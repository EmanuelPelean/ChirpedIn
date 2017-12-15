<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<style>
img {
    display: block;
    margin: 0 auto;
}
body{
  padding:0;
  margin:0;
}
.vid-container{
  position:relative;
  height:100vh;
  overflow:hidden;
}
.bgvid{
  position:absolute;
  left:0;
  top:0;
  width:100vw;
}
.inner-container{
  width:400px;
  height:400px;
  position:absolute;
  top:calc(50vh - 200px);
  left:calc(50vw - 200px);
  overflow:hidden;
}
.bgvid.inner{
  top:calc(-50vh + 200px);
  left:calc(-50vw + 200px);
  filter: url("data:image/svg+xml;utf9,<svg%20version='1.1'%20xmlns='http://www.w3.org/2000/svg'><filter%20id='blur'><feGaussianBlur%20stdDeviation='10'%20/></filter></svg>#blur");
  -webkit-filter:blur(10px);
  -ms-filter: blur(10px);
  -o-filter: blur(10px);
  filter:blur(10px);
}
.box{
  position:absolute;
  height:100%;
  width:100%;
  font-family:Helvetica;
  color:#fff;
  background:rgba(0,0,0,0.13);
  padding:10px 0px;
}
.box h1{
  text-align: center;
  margin:15px 0;
  font-size:45px;
}
.box h2{
  text-align:center;
  margin:15px 0;
  font-size:15px;
  font-weight: 200;
}
.box input{
  display:block;
  width:300px;
  margin:20px auto;
  padding:15px;
  background:rgba(0,0,0,0.2);
  color:#fff;
  border:0;
}
.box input:focus,.box input:active,.box button:focus,.box button:active{
  outline:none;
}
.box button{
  background:#2ecc71;
  border:0;
  color:#fff;
  padding:10px;
  font-size:20px;
  width:330px;
  margin:20px auto;
  display:block;
  cursor:pointer;
}
.box button:active{
  background:#27ae60;
}
.box p{
  font-size:14px;
  text-align:center;
}
.box p span{
  cursor:pointer;
  color:#666;
}</style>
</head>
<body>


<div class="vid-container">
  <video class="bgvid" autoplay="autoplay" muted="muted" preload="auto" loop>
      <source src="http://mazwai.com/system/posts/videos/000/000/109/webm/leif_eliasson--glaciartopp.webm?1410742112" type="video/webm">
  </video>
  <div class="inner-container">
    <video class="bgvid inner" autoplay="autoplay" muted="muted" preload="auto" loop>
      <source src="http://mazwai.com/system/posts/videos/000/000/109/webm/leif_eliasson--glaciartopp.webm?random=1" type="video/webm">
    </video>
    <div class="box">
    <img alt="ChirpedInLogo" height="50%" id="hplogo" src="https://files.slack.com/files-tmb/T04UPFZ31-F8F6YBURJ-8516b8475f/chirpedinfinal_360.png">
      <h1>ChirpedIn</h1>
      <h2>Connect with your Grand Circus peers like never before.</h2>
      <button type="submit" onclick="relocate_home()">Login with LinkedIn!</button>
    </div>
  </div>
</div>

<script>
function relocate_home()
{
     location.href = "https://www.linkedin.com/oauth/v2/authorization?&response_type=code&client_id=86n54w1pjwv6o5&redirect_uri=http://localhost:8080/ChirpedIn/result&scope=r_basicprofile r_emailaddress&state=12345";
} 
</script>

</body>
</html>