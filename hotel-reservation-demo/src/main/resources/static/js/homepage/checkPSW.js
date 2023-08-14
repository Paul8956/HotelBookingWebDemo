var newPassword = document.getElementById("lname").value;
console.log(newPassword);
function checkPasswordMatch() {
        var newPassword = document.getElementById("newpsw").value;
        var confirmPassword = document.getElementById("confirmuserpsw").value;
        var message = document.getElementById("passwordMatchMessage");

        if (newPassword === confirmPassword) {
            message.textContent = "Passwords match";
            message.style.color = "green";
        } else {
            message.textContent = "Passwords do not match";
            message.style.color = "red";
        }
}