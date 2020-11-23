<!DOCTYPE HTML>
<html>
<head>
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
<style type="text/css">
    span {
        color: green;
        font-size: 12px;
        padding-left: 5px;
    }
    pre {
        width: 500px;
        border-style: solid;
        border-width: 1px;
        background: #FOFOFO;
        margin-left: 5px;
        margin-top: -1px;
        padding: 10px;
    }
</style>
    <title>Code</title>
</head>
<body>
<#if code??>
<span id="load_date">${code.date}</span>
<#if code.expiringByViews && code.views gte 0>
<span id="views_restriction">${code.views} more views allowed</span>
</#if>
<#if code.expiringByTime && code.time gte 0>
<span id="time_restriction">The code will be available for ${code.time} seconds</span>
</#if>
<pre id="code_snippet"><code>${code.code}</code></pre>
<#else>
No snippets
</#if>
</body>
</html>
