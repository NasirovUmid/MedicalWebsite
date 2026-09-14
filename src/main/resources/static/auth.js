const form = document.querySelector('.auth-form');
const message = document.querySelector('#form-message');
const switchText = document.querySelector('.auth-subtitle');

if (switchText) {
  switchText.insertAdjacentHTML('beforeend', ' · <a href="index.html">На главную</a>');
}

function showMessage(text, type) {
  message.textContent = text;
  message.className = `message ${type}`;
}

async function send(path, body) {
  const response = await fetch(path, { method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(body) });
  if (!response.ok) {
    const problem = await response.json().catch(() => null);
    throw new Error(problem?.message || 'Не удалось выполнить запрос. Проверьте данные и повторите попытку.');
  }
  return response.json();
}

form?.addEventListener('submit', async event => {
  event.preventDefault();
  const data = Object.fromEntries(new FormData(form));
  const isRegister = form.id === 'register-form';
  if (isRegister && data.password !== data.confirmPassword) {
    showMessage('Пароли не совпадают.', 'error');
    return;
  }
  try {
    const payload = isRegister ? {
      fullName: data.fullName,
      email: data.email,
      phoneNumber: data.phoneNumber.replaceAll(' ', ''),
      password: data.password,
      birthDate: new Date(`${data.birthDate}T00:00:00.000Z`).toISOString(),
      role: 'PATIENT',
      status: 'ACTIVE'
    } : { email: data.email, password: data.password };
    const result = await send(isRegister ? '/auth/register' : '/auth/login', payload);
    if (result.accessToken) localStorage.setItem('accessToken', result.accessToken);
    if (result.refreshToken) localStorage.setItem('refreshToken', result.refreshToken);
    if (result.userId) localStorage.setItem('userId', result.userId);
    showMessage(isRegister ? 'Аккаунт создан. Добро пожаловать в DentaVita!' : 'Вход выполнен. Добро пожаловать!', 'success');
    window.setTimeout(() => window.location.href = isRegister ? 'index.html' : 'profile.html', 900);
  } catch (error) {
    showMessage(error.message, 'error');
  }
});
