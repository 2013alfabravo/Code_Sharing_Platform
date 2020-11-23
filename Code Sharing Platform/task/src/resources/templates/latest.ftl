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
        padding-left: 10px;
    }

    code {
        width: 500px;
        border-style: solid;
        border-width: 1px;
        background: #FOFOFO;
        margin-left: 5px;
        margin-top: -10px;
        padding: 10px;
    }
</style>
    <title>Latest</title>
</head>
<body>
<#list snippets as snippet>
<span id="load_date">${snippet.date}</span>
    <#if snippet.expiringByViews && snippet.views gte 0>
        <span id="views_restriction">${snippet.views} more views allowed</span>
    </#if>
    <#if snippet.expiringByTime && snippet.time gte 0>
        <span id="time_restriction">The code will be available for ${snippet.time} seconds</span>
    </#if>
<pre id="code_snippet"><code>${snippet.code}</code></pre>
<br>
</#list>
</body>
</html>
