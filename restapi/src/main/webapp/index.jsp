<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>API Services</title>
  <link rel="stylesheet" href="https://stackedit.io/style.css" />
</head>

<body class="stackedit">
  <div class="stackedit__html"><h1 id="tychexplore-supported-rest-apis">TychExplore Supported REST APIs</h1>
<p>On this page you will find description of all REST API services provided by the REST Web Service application of tychexplore. Some of them are</p>
<ul>
<li><strong>getGraphData</strong></li>
<li><strong>getBlockBySearch</strong></li>
<li><strong>getRecentBlocks</strong></li>
<li><strong>getTotalPages</strong></li>
<li><strong>totalMinedCoins</strong></li>
</ul>
<p>Each of these methods are explained below</p>
<h2 id="getgraphdata">getGraphData</h2>
<p>Returns 30 data samples for plotting difficulty graph in tychexplore at regular interval between height 0 and top block height.</p>

<table>
<thead>
<tr>
<th>Method</th>
<th>Request Method</th>
<th>Parameter</th>
<th>Sample Request</th>
</tr>
</thead>
<tbody>
<tr>
<td>getGraphData</td>
<td><code>GET</code></td>
<td></td>
<td>&lsquo;<a href="http://explorer.tychecash.net/restapi/getGraphData">http://explorer.tychecash.net/restapi/getGraphData</a>&rsquo;</td>
</tr>
</tbody>
</table><p><strong>Sample Response</strong></p>
<blockquote>
<p>{&ldquo;id&rdquo;:&ldquo;self&rdquo;,&ldquo;blockHeaders&rdquo;:[{&ldquo;timestamp&rdquo;:&ldquo;1518206850&rdquo;,&ldquo;orphan_status&rdquo;:&ldquo;false&rdquo;,&ldquo;minor_version&rdquo;:&ldquo;0&rdquo;,&ldquo;height&rdquo;:1712,&ldquo;reward&rdquo;:&ldquo;303189.24102237&rdquo;,<br>
&ldquo;nonce&rdquo;:&ldquo;68313&rdquo;,&ldquo;hash&rdquo;:&ldquo;b31689afa06daf1127ace559ffa28b836c2167f901a43fc1a69b51e34396b78c&rdquo;,&ldquo;major_version&rdquo;:&ldquo;1&rdquo;,<br>
&ldquo;difficulty&rdquo;:&ldquo;701432&rdquo;,&ldquo;depth&rdquo;:&ldquo;49657&rdquo;,&ldquo;prev_hash&rdquo;:&ldquo;e26676ddf28f4f0b7428a24149c14f044aed15eebdfcb7b1db83444f8737f351&rdquo;},<br>
?<br>
],&ldquo;jsonrpc&rdquo;:&ldquo;2.0&rdquo;,&ldquo;status&rdquo;:&ldquo;SUCCESS&rdquo;}</p>
</blockquote>
<h2 id="getblockbysearch">getBlockBySearch</h2>
<p>Block Search either by <em>hash</em> or <em>height</em>. Requires request parameter <strong>query</strong> as key</p>

<table>
<thead>
<tr>
<th>Method</th>
<th>Request Method</th>
<th>Parameter</th>
<th>Sample Request</th>
</tr>
</thead>
<tbody>
<tr>
<td>getBlockBySearch</td>
<td><code>GET</code></td>
<td>query</td>
<td>&lsquo;<a href="http://explorer.tychecash.net/restapi/getBlockBySearch?query=51362">http://explorer.tychecash.net/restapi/getBlockBySearch?query=51362</a>&rsquo;</td>
</tr>
</tbody>
</table><p><strong>Sample Response</strong></p>
<blockquote>
<p>{&ldquo;id&rdquo;:&ldquo;self&rdquo;,&ldquo;result&rdquo;:{&ldquo;status&rdquo;:&ldquo;OK&rdquo;,&ldquo;block_header&rdquo;:{&ldquo;timestamp&rdquo;:&ldquo;1525343190&rdquo;,&ldquo;orphan_status&rdquo;:&ldquo;false&rdquo;,&ldquo;minor_version&rdquo;:&ldquo;0&rdquo;,&ldquo;height&rdquo;:51362,&ldquo;reward&rdquo;:&ldquo;250875.57219056&rdquo;,<br>
&ldquo;nonce&rdquo;:&ldquo;1104312160&rdquo;,&ldquo;hash&rdquo;:&ldquo;4f725d3240fcd304318ecb11ca752bfc5e427387595856f94f65a61de2ff0ddc&rdquo;,&ldquo;major_version&rdquo;:&ldquo;1&rdquo;,<br>
&ldquo;difficulty&rdquo;:&ldquo;36143646&rdquo;,&ldquo;depth&rdquo;:&ldquo;11&rdquo;,&ldquo;prev_hash&rdquo;:&ldquo;93ee3d85ef608a7c666deeab35763c78071f39c1023ba3d0619f08b4b5fdfe58&rdquo;}},&ldquo;jsonrpc&rdquo;:&ldquo;2.0&rdquo;}</p>
</blockquote>

<table>
<thead>
<tr>
<th>Method</th>
<th>Request Method</th>
<th>Parameter</th>
<th>Sample Request</th>
</tr>
</thead>
<tbody>
<tr>
<td>getBlockBySearch</td>
<td><code>GET</code></td>
<td>query</td>
<td>&lsquo;<a href="http://explorer.tychecash.net/restapi/getBlockBySearch?query=e26676ddf28f4f0b7428a24149c14f044aed15eebdfcb7b1db83444f8737f351">http://explorer.tychecash.net/restapi/getBlockBySearch?query=e26676ddf28f4f0b7428a24149c14f044aed15eebdfcb7b1db83444f8737f351</a>&rsquo;</td>
</tr>
</tbody>
</table><p><strong>Sample Response</strong></p>
<blockquote>
<p>{&ldquo;id&rdquo;:&ldquo;self&rdquo;,&ldquo;result&rdquo;:{&ldquo;status&rdquo;:&ldquo;OK&rdquo;,&ldquo;block_header&rdquo;:{&ldquo;timestamp&rdquo;:&ldquo;1518206844&rdquo;,&ldquo;orphan_status&rdquo;:&ldquo;false&rdquo;,&ldquo;minor_version&rdquo;:&ldquo;0&rdquo;,&ldquo;height&rdquo;:1711,&ldquo;reward&rdquo;:&ldquo;303190.39760195&rdquo;,<br>
&ldquo;nonce&rdquo;:&ldquo;50334315&rdquo;,&ldquo;hash&rdquo;:&ldquo;e26676ddf28f4f0b7428a24149c14f044aed15eebdfcb7b1db83444f8737f351&rdquo;,&ldquo;major_version&rdquo;:&ldquo;1&rdquo;,<br>
&ldquo;difficulty&rdquo;:&ldquo;697657&rdquo;,&ldquo;depth&rdquo;:&ldquo;49663&rdquo;,&ldquo;prev_hash&rdquo;:&ldquo;a823cdfc8e2fffb0513020169599e31ec22835b9976420721fd19637f480f932&rdquo;}},&ldquo;jsonrpc&rdquo;:&ldquo;2.0&rdquo;}</p>
</blockquote>
<h2 id="getrecentblocks">getRecentBlocks</h2>
<p>Returns recent n blocks in descending order of height, usually used to display recently mined blocks in table . Parameters are filterslength defaults to 0, pagenum is used in logic (lastblockheight - (pagesize* pageNumber)) - i internally to get relative next set of blocks where pagesize is number of blocks to be displayed in current page.</p>

<table>
<thead>
<tr>
<th>Method</th>
<th>Request Method</th>
<th>Parameter</th>
<th>Sample Request</th>
</tr>
</thead>
<tbody>
<tr>
<td>getGraphData</td>
<td><code>GET</code></td>
<td>filterslength,pagenum,pagesize</td>
<td>&lsquo;<a href="http://explorer.tychecash.net/restapi/getRecentBlocks?filterslength=0&amp;pagenum=1&amp;pagesize=10">http://explorer.tychecash.net/restapi/getRecentBlocks?filterslength=0&amp;pagenum=1&amp;pagesize=10</a>&rsquo;</td>
</tr>
</tbody>
</table><p><strong>Sample Response</strong></p>
<blockquote>
<p>{&ldquo;id&rdquo;:&ldquo;self&rdquo;,&ldquo;blockHeaders&rdquo;:[{&ldquo;timestamp&rdquo;:&ldquo;1525345669&rdquo;,&ldquo;orphan_status&rdquo;:&ldquo;false&rdquo;,&ldquo;minor_version&rdquo;:&ldquo;0&rdquo;,&ldquo;height&rdquo;:51375,&ldquo;reward&rdquo;:&ldquo;250863.13128864&rdquo;,<br>
&ldquo;nonce&rdquo;:&ldquo;2684453221&rdquo;,&ldquo;hash&rdquo;:&ldquo;5f8bc9dc24352a9558ab1e71db907c2041ca67893f7279d13bad77c9ce4b9063&rdquo;,&ldquo;major_version&rdquo;:&ldquo;1&rdquo;,<br>
&ldquo;difficulty&rdquo;:&ldquo;35746593&rdquo;,&ldquo;depth&rdquo;:&ldquo;10&rdquo;,&ldquo;prev_hash&rdquo;:&ldquo;0e1c8c8ca5a3dc3187ac7eedd3af66e25fd743c13bdcb3a08c7962224f937ffb&rdquo;},<br>
?<br>
],&ldquo;jsonrpc&rdquo;:&ldquo;2.0&rdquo;,&ldquo;status&rdquo;:&ldquo;SUCCESS&rdquo;}</p>
</blockquote>
<h2 id="gettotalpages">getTotalPages</h2>
<p>Returns the top most block height or the current blockchain height.</p>

<table>
<thead>
<tr>
<th>Method</th>
<th>Request Method</th>
<th>Parameter</th>
<th>Sample Request</th>
</tr>
</thead>
<tbody>
<tr>
<td>getTotalPages</td>
<td><code>GET</code></td>
<td></td>
<td>&lsquo;<a href="http://explorer.tychecash.net/restapi/getTotalPages">http://explorer.tychecash.net/restapi/getTotalPages</a>&rsquo;</td>
</tr>
</tbody>
</table><p><strong>Sample Response</strong></p>
<blockquote>
<p>51389</p>
</blockquote>
<h2 id="totalminedcoins">totalMinedCoins</h2>
<p>Returns the total mined coins in tychecash network, both numeric and word format is available in response json. Word format is rounded to integer part alone whereas numeric part is available to 8 decimal places.</p>

<table>
<thead>
<tr>
<th>Method</th>
<th>Request Method</th>
<th>Parameter</th>
<th>Sample Request</th>
</tr>
</thead>
<tbody>
<tr>
<td>getTotalPages</td>
<td><code>GET</code></td>
<td></td>
<td>&lsquo;<a href="http://explorer.tychecash.net/restapi/totalMinedCoins">http://explorer.tychecash.net/restapi/totalMinedCoins</a>&rsquo;</td>
</tr>
</tbody>
</table><p><strong>Sample Response</strong></p>
<blockquote>
<p>{&ldquo;inWords&rdquo;:&ldquo;fourteen billion two hundred forty two million sixty thousand four hundred sixty six&rdquo;,&ldquo;totalCoins&rdquo;:&ldquo;14242060465.43596548&rdquo;}</p>
</blockquote>
</div>
</body>

</html>
