<#import "parts/common.ftlh" as c>

<@c.page>
<h2>We send mail to you.</h2>
<h2>Please, check your email.</h2>

<form id = myForm name = forma  method="post" onSubmit="checkForm()" action="/registrationSuccession/{">
    <a href="/login">Back to login page</a>
    <button id="sndMail" type="submit">Send mail again</button>
<!--    <input type="button" id="sbmitBut" value ="Send mail again" onClick = "checkForm()">-->
</form>

<script type="text/javascript">
var btn = document.getElementById("sndMail");
btn.onclick = function() {
    btn.disabled = true; // disable button
    window.setTimeout(function() {
       btn.disabled = false; // enable button
    }, 9000 /* 9 sec */);
};



 function timer(){

    var obj=document.getElementById('sbmitBut');
    var regexp = /(\d+)/i;
    var RealTimer = regexp.exec(obj.innerHTML)[0];

    if (--RealTimer < 0) RealTimer = 0;

    obj.innerHTML = obj.value + '[ '+RealTimer+' ]';

    if (RealTimer==0) {
	   location.replace("https://realadmin.ru/coding/button-timer-js.html");
	   return;
    }
    else { setTimeout(timer,90); }
  }

 setTimeout(timer,1000);


</script>


</@c.page>