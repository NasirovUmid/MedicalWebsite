async function loadAdmin() {
  if (!userId) { location.href = 'login.html'; return; }
  try {
    const current = await getCurrentUser();
    if (current.role !== 'ADMIN') { location.href = 'profile.html'; return; }
    setHeaderUser(current);
  } catch {
    location.href = 'login.html';
  }
}

document.addEventListener('DOMContentLoaded', loadAdmin);
