<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/css/styles.css">
    <title>Code</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.4.0/styles/a11y-dark.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>

</head>
<body>
<main class="code-container">
    <span id="load_date" class="date">${code.getDateString()}</span> <br>


    <#if code.isTimeRestricted() && code.getTime() gte 0>
        <span id="time_restriction" class="time-span"> The code will be available for ${code.getTime()} seconds</span> <br>
    </#if>

    <#if code.isViewRestricted() && code.getViews() gte 0>
        <span id="views_restriction" class="views-span"> ${code.getViews()}  more views allowed</span> <br>
    </#if>
    <pre id="code_snippet" class="code"><code>${code.code}</code></pre>
</main>
</body>
</html>