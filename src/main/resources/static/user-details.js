async function request(path) {
    const response = await fetch(path, { headers: authHeaders });
    if (!response.ok) throw new Error();
    return response.json();
}

function roleLabel(role) {
    return ({ PATIENT: 'Пациент', DOCTOR: 'Врач', ADMIN: 'Администратор' })[role] || role;
}

async function loadUserDetails() {
    const urlParams = new URLSearchParams(window.location.search);
    const userId = urlParams.get('userId');
    
    if (!userId) {
        document.querySelector('#user-details-container').innerHTML = '<p>Идентификатор пользователя не указан.</p>';
        return;
    }

    try {
        const user = await request(`/users/${userId}`);
        
        const actions = user.role === 'PATIENT' 
            ? `<div class="user-actions">
                <a class="button small" href="appointment.html?patientId=${encodeURIComponent(user.id)}">Добавить appointment</a>
                <a class="outline small" href="form043.html?userId=${encodeURIComponent(user.id)}">Форма 043</a>
               </div>` 
            : '';

        document.querySelector('#user-details-container').innerHTML = `
            <article class="user-details">
                <div class="details-heading">
                    <div class="avatar">${escapeHtml(user.fullName?.[0] || '?')}</div>
                    <div>
                        <p class="eyebrow">КАРТОЧКА ПОЛЬЗОВАТЕЛЯ</p>
                        <h2>${escapeHtml(user.fullName)}</h2>
                        <p>${roleLabel(user.role)}</p>
                    </div>
                </div>
                <dl>
                    <dt>Email</dt>
                    <dd>${escapeHtml(user.email)}</dd>
                    <dt>Телефон</dt>
                    <dd>${escapeHtml(user.phoneNumber || '—')}</dd>
                    <dt>Дата рождения</dt>
                    <dd>${user.birthDate ? new Date(user.birthDate).toLocaleDateString('ru-RU') : '—'}</dd>
                    <dt>Статус</dt>
                    <dd>${escapeHtml(user.userStatus || user.status || '—')}</dd>
                </dl>
                ${actions}
            </article>
        `;
    } catch (error) {
        document.querySelector('#user-details-container').innerHTML = '<p>Не удалось загрузить карточку пользователя.</p>';
    }
}

async function initUserDetails() {
    if (!userId) {
        location.href = 'login.html';
        return;
    }

    try {
        const current = await getCurrentUser();
        if (current.role !== 'ADMIN') {
            location.href = 'profile.html';
            return;
        }
        setHeaderUser(current);
        await loadUserDetails();
    } catch {
        location.href = 'login.html';
    }
}

document.addEventListener('DOMContentLoaded', () => {
    initUserDetails();
    document.querySelector('#back-users').addEventListener('click', () => {
        location.href = 'users.html';
    });
});
