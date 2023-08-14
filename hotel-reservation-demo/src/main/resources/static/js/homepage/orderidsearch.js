document.getElementById("searchbyorderid").addEventListener("click", function() {
        var inputValue = document.getElementById("orderid").value;
        if(!isNaN(inputValue) && inputValue != "0"){
            var url = "/orderlist/" + inputValue;
            window.location.href = url;
        }
});