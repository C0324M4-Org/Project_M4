document.getElementById('eyeToggle').addEventListener('click', function () {
    let passwordField = document.getElementById('password');
    let icon = this.querySelector('i');
    if (passwordField.type === 'password') {
        passwordField.type = 'text';
        icon.classList.remove('bi-eye');
        icon.classList.add('bi-eye-slash'); // Thay đổi biểu tượng thành "mắt bị che"
    } else {
        passwordField.type = 'password';
        icon.classList.remove('bi-eye-slash');
        icon.classList.add('bi-eye');
    }
});

document.getElementById('eyeToggleConfirm').addEventListener('click', function () {
    let confirmPasswordField = document.getElementById('confirmPassword');
    let icon = this.querySelector('i');
    if (confirmPasswordField.type === 'password') {
        confirmPasswordField.type = 'text';
        icon.classList.remove('bi-eye');
        icon.classList.add('bi-eye-slash');
    } else {
        confirmPasswordField.type = 'password';
        icon.classList.remove('bi-eye-slash');
        icon.classList.add('bi-eye');
    }
});

