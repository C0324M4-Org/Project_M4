document.getElementById('eyeToggle').addEventListener('click', function () {
    var passwordField = document.getElementById('password');
    var icon = this.querySelector('i');
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
    var confirmPasswordField = document.getElementById('confirmPassword');
    var icon = this.querySelector('i');
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

