function showAlert() {
    alert("The button was clicked!");
}
function showName(name) {
    alert("Here's the name: " + name[0].description);
}



document.getElementById("re-directed").addEventListener("click", function() {
        var selectedValue = document.getElementById("countrySelect").value;
        if(selectedValue != "0"){
            var url = "/home/" + selectedValue;
            window.location.href = url;
        }
});






