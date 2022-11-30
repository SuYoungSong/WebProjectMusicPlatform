<%--
  Created by IntelliJ IDEA.
  User: prodr
  Date: 2022-11-30-030
  Time: 오후 2:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://unpkg.com/wavesurfer.js"></script>
<style>
    *{
        padding: 0;
        margin: 0;
        font-family: 'sans-serif';
    }
    .hero{
        width: 100%;
        height: 100vh;
        background: #a9a9a9;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .music{
        width:90%;
        max-width: 900px;
        padding: 30px;
        box-shadow: 0 0 20px rgba(red, 0, 0, 0.4);
        background: #fff;
        color: #555;
        border-radius: 8px;
    }
    .track{
        display:flex;
        align-items: center;
        margin-top: 20px;
    }
    .track img{
        width: 70px;
        margin-right: 30px;
        cursor: pointer;
    }
    .track div{
        flex: 1;
    }
</style>

</head>
<body>
<div class="hero">
    <div class="music">
        <p>Artist: NEFFEX</p>
        <h1>Good Day (Wake Up)</h1>
        <div class="track">
            <img src="resources/images/defaultPlayImage.png" id="playBtn">
            <div id="waveform"></div>
        </div>
    </div>
</div>

<script>
    var playBtn = document.getElementById("playBtn")


    var wavesurfer = WaveSurfer.create({
        container: '#waveform',
        waveColor: 'violet',
        progressColor: '#ff006c',
        barWidth: 4,
        responsive: true,
        height: 150,
        barRadius: 4
    });

    wavesurfer.load("resources/musics/ffc207cf-b641-4d3a-b45b-8872a13afda4.mp3");

    playBtn.onclick = function() {
        wavesurfer.play();
        wavesurfer.setCurrentTime(31)
    }

    wavesurfer.on('finish', function (){
        wavesurfer.stop();

    })

</script>

</body>
</html>
