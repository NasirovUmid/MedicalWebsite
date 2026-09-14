const token = localStorage.getItem('accessToken');
const userId = localStorage.getItem('userId');
const authHeaders = token ? { Authorization: `Bearer ${token}` } : {};
const pageStyles = document.createElement('link');
pageStyles.rel = 'stylesheet';
pageStyles.href = 'pages.css';
document.head.append(pageStyles);

async function getCurrentUser() {
  if (!userId) return null;
  const response = await fetch(`/users/${userId}`, { headers: authHeaders });
  if (!response.ok) throw new Error('Не удалось получить данные пользователя');
  return response.json();
}

function setHeaderUser(user) {
  const profile = document.querySelector('[data-profile-link]');
  const admin = document.querySelector('[data-admin-link]');
  if (profile) {
    profile.href = user ? 'profile.html' : 'login.html';
    profile.textContent = user ? `Профиль: ${user.fullName.split(' ')[0]}` : 'Войти';
  }
  if (admin && user?.role === 'ADMIN') admin.hidden = false;
}

async function initHeader() {
  try { setHeaderUser(await getCurrentUser()); } catch { setHeaderUser(null); }
}

function escapeHtml(value = '') { return String(value).replace(/[&<>'"]/g, char => ({ '&':'&amp;', '<':'&lt;', '>':'&gt;', "'":'&#39;', '"':'&quot;' }[char])); }
function logout() { localStorage.removeItem('accessToken'); localStorage.removeItem('refreshToken'); localStorage.removeItem('userId'); location.href = 'index.html'; }
document.addEventListener('DOMContentLoaded', initHeader);
