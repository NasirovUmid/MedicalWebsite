const formUserId = new URLSearchParams(location.search).get('userId');
async function loadForm() { try { const response = await fetch(`/form043/${formUserId}/user`, { headers: authHeaders }); if (!response.ok) throw new Error(); const data = await response.json(); document.querySelector('#form-content').innerHTML = `<pre>${escapeHtml(JSON.stringify(data, null, 2))}</pre>`; } catch { document.querySelector('#form-content').textContent = 'Не удалось получить форму 043.'; } }
document.addEventListener('DOMContentLoaded', loadForm);
