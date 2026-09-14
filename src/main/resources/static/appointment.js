const selectedPatient = new URLSearchParams(location.search).get('patientId');
const appointmentForm = document.querySelector('#appointment-form'); const appointmentMessage = document.querySelector('#form-message');
function option(item, label) { return `<option value="${item.id}">${escapeHtml(label)}</option>`; }
async function loadOptions() { try { const [usersPage, services] = await Promise.all([fetch('/users?size=100&userStatus=ACTIVE', { headers: authHeaders }).then(r => r.json()), fetch('/services', { headers: authHeaders }).then(r => r.json())]); const patients = usersPage.content.filter(user => user.role === 'PATIENT'); const doctors = usersPage.content.filter(user => user.role === 'DOCTOR'); document.querySelector('#patient').innerHTML = patients.map(user => option(user, user.fullName)).join(''); document.querySelector('#doctor').innerHTML = doctors.map(user => option(user, user.fullName)).join(''); document.querySelector('#service').innerHTML = services.map(service => option(service, `${service.servicesType} — ${service.price} сум`)).join(''); if (selectedPatient) document.querySelector('#patient').value = selectedPatient; } catch { appointmentMessage.textContent = 'Не удалось загрузить список пользователей или услуг.'; appointmentMessage.className = 'message error'; } }
const scheduleToggle = document.querySelector('#create-schedule');
const scheduleInputs = document.querySelector('#schedule-inputs');
scheduleToggle.addEventListener('change', () => { scheduleInputs.hidden = !scheduleToggle.checked; });

function dayOfWeek(date) { return ['SUNDAY', 'MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY'][date.getDay()]; }

appointmentForm.addEventListener('submit', async event => {
  event.preventDefault();
  const data = Object.fromEntries(new FormData(appointmentForm));
  try {
    const appointmentDate = new Date(data.appointmentDate);
    const response = await fetch('/appointments', { method:'POST', headers:{ 'Content-Type':'application/json', ...authHeaders }, body:JSON.stringify({ doctorId:data.doctorId, patientId:data.patientId, serviceId:data.serviceId, appointmentDate:appointmentDate.toISOString(), status:'SCHEDULED' }) });
    if (!response.ok) throw new Error();
    if (scheduleToggle.checked) {
      if (!data.scheduleStart || !data.scheduleEnd || data.scheduleStart >= data.scheduleEnd) throw new Error('schedule');
      const scheduleResponse = await fetch('/schedules/schedules', { method:'POST', headers:{ 'Content-Type':'application/json', ...authHeaders }, body:JSON.stringify({ doctorId:data.doctorId, dayOfWeek:dayOfWeek(appointmentDate), startTime:data.scheduleStart, endTime:data.scheduleEnd, active:true }) });
      if (!scheduleResponse.ok) throw new Error('schedule');
    }
    appointmentMessage.textContent = scheduleToggle.checked ? 'Appointment и medical schedule созданы. Запись уже видна пациенту в профиле.' : 'Appointment создан. Запись уже видна пациенту в профиле.';
    appointmentMessage.className = 'message success';
  } catch (error) { appointmentMessage.textContent = error.message === 'schedule' ? 'Appointment создан, но medical schedule не добавлен: проверьте время интервала.' : 'Не удалось создать appointment.'; appointmentMessage.className = 'message error'; }
});
document.addEventListener('DOMContentLoaded', loadOptions);
