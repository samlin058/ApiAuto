<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <title>ApiAuto 测试报告 - ${suiteName!}</title>
  <style>
    :root {
      --bg: #f5f7fa;
      --card: #ffffff;
      --text: #1f2937;
      --muted: #6b7280;
      --pass: #059669;
      --fail: #dc2626;
      --skip: #d97706;
      --border: #e5e7eb;
      --accent: #2563eb;
    }
    * { box-sizing: border-box; }
    body {
      margin: 0;
      font-family: "Segoe UI", "PingFang SC", "Microsoft YaHei", sans-serif;
      background: var(--bg);
      color: var(--text);
      line-height: 1.5;
    }
    .wrap { max-width: 1200px; margin: 0 auto; padding: 24px 16px 48px; }
    h1 { margin: 0 0 4px; font-size: 1.6rem; }
    .meta { color: var(--muted); font-size: 0.9rem; margin-bottom: 20px; }
    .summary {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(140px, 1fr));
      gap: 12px;
      margin-bottom: 24px;
    }
    .card {
      background: var(--card);
      border: 1px solid var(--border);
      border-radius: 10px;
      padding: 14px 16px;
    }
    .card .label { color: var(--muted); font-size: 0.8rem; }
    .card .value { font-size: 1.5rem; font-weight: 700; margin-top: 4px; }
    .value.pass { color: var(--pass); }
    .value.fail { color: var(--fail); }
    .value.skip { color: var(--skip); }
    .value.rate { color: var(--accent); }
    h2 { margin: 28px 0 12px; font-size: 1.2rem; }
    .tabs {
      display: flex;
      gap: 8px;
      margin-bottom: 12px;
      flex-wrap: wrap;
    }
    .tab-btn {
      appearance: none;
      border: 1px solid var(--border);
      background: var(--card);
      color: var(--text);
      border-radius: 999px;
      padding: 8px 16px;
      font-size: 0.9rem;
      font-weight: 600;
      cursor: pointer;
      display: inline-flex;
      align-items: center;
      gap: 8px;
      transition: background .15s, border-color .15s, color .15s, box-shadow .15s;
    }
    .tab-btn:hover { border-color: #cbd5e1; background: #f8fafc; }
    .tab-btn .count {
      font-size: 0.75rem;
      font-weight: 700;
      min-width: 20px;
      height: 20px;
      padding: 0 6px;
      border-radius: 999px;
      background: #e5e7eb;
      color: #374151;
      display: inline-flex;
      align-items: center;
      justify-content: center;
    }
    .tab-btn.active[data-tab="fail"] {
      background: #fef2f2;
      border-color: #fecaca;
      color: var(--fail);
      box-shadow: 0 0 0 2px rgba(220, 38, 38, 0.08);
    }
    .tab-btn.active[data-tab="fail"] .count { background: var(--fail); color: #fff; }
    .tab-btn.active[data-tab="skip"] {
      background: #fffbeb;
      border-color: #fde68a;
      color: var(--skip);
      box-shadow: 0 0 0 2px rgba(217, 119, 6, 0.08);
    }
    .tab-btn.active[data-tab="skip"] .count { background: var(--skip); color: #fff; }
    .tab-btn.active[data-tab="pass"] {
      background: #ecfdf5;
      border-color: #a7f3d0;
      color: var(--pass);
      box-shadow: 0 0 0 2px rgba(5, 150, 105, 0.08);
    }
    .tab-btn.active[data-tab="pass"] .count { background: var(--pass); color: #fff; }
    .panel {
      display: none;
      background: var(--card);
      border: 1px solid var(--border);
      border-radius: 10px;
      overflow: hidden;
    }
    .panel.active { display: block; }
    table {
      width: 100%;
      border-collapse: collapse;
    }
    th, td {
      padding: 10px 12px;
      border-bottom: 1px solid var(--border);
      text-align: left;
      font-size: 0.9rem;
      vertical-align: top;
    }
    th { background: #f9fafb; color: var(--muted); font-weight: 600; }
    tr:last-child > td { border-bottom: none; }
    .badge {
      display: inline-block;
      padding: 2px 8px;
      border-radius: 999px;
      font-size: 0.75rem;
      font-weight: 600;
      color: #fff;
    }
    .badge.PASS { background: var(--pass); }
    .badge.FAIL { background: var(--fail); }
    .badge.SKIP { background: var(--skip); }
    tr.case-row.clickable { cursor: pointer; }
    tr.case-row.clickable:hover { background: #fef2f2; }
    tr.case-row.clickable.open { background: #fef2f2; }
    .expand-hint {
      color: var(--muted);
      font-size: 0.75rem;
      margin-left: 6px;
    }
    tr.case-row.clickable.open .expand-hint::after { content: "收起"; }
    tr.case-row.clickable:not(.open) .expand-hint::after { content: "展开详情"; }
    tr.detail-row { display: none; }
    tr.detail-row.open { display: table-row; }
    tr.detail-row > td {
      background: #fffafa;
      border-bottom: 1px solid #fecaca;
      padding: 0 12px 14px;
    }
    .detail-panel { padding-top: 4px; }
    .kv { margin: 6px 0; font-size: 0.9rem; }
    .kv b { color: var(--muted); font-weight: 600; }
    pre {
      background: #111827;
      color: #e5e7eb;
      padding: 12px;
      border-radius: 8px;
      overflow: auto;
      font-size: 0.78rem;
      max-height: 320px;
      white-space: pre-wrap;
      word-break: break-word;
    }
    .http-item {
      border-left: 3px solid var(--accent);
      padding-left: 10px;
      margin: 10px 0;
    }
    .muted { color: var(--muted); }
    .small { font-size: 0.8rem; }
    .empty { padding: 28px 16px; color: var(--muted); font-size: 0.9rem; text-align: center; }
  </style>
</head>
<body>
<div class="wrap">
  <h1>ApiAuto 接口测试报告</h1>
  <div class="meta">
    套件：${suiteName!} &nbsp;|&nbsp; 生成时间：${generatedAt!} &nbsp;|&nbsp; 总耗时：${durationText!} (${durationMs!} ms)
  </div>

  <div class="summary">
    <div class="card"><div class="label">总用例数</div><div class="value">${total!0}</div></div>
    <div class="card"><div class="label">通过</div><div class="value pass">${pass!0}</div></div>
    <div class="card"><div class="label">失败</div><div class="value fail">${fail!0}</div></div>
    <div class="card"><div class="label">跳过</div><div class="value skip">${skip!0}</div></div>
    <div class="card"><div class="label">通过率</div><div class="value rate">${passRate!'N/A'}</div></div>
  </div>

  <h2>全部用例</h2>

  <div class="tabs" role="tablist">
    <button type="button" class="tab-btn active" data-tab="fail" onclick="switchTab('fail')" role="tab" aria-selected="true">
      失败 <span class="count">${fail!0}</span>
    </button>
    <button type="button" class="tab-btn" data-tab="skip" onclick="switchTab('skip')" role="tab" aria-selected="false">
      跳过 <span class="count">${skip!0}</span>
    </button>
    <button type="button" class="tab-btn" data-tab="pass" onclick="switchTab('pass')" role="tab" aria-selected="false">
      通过 <span class="count">${pass!0}</span>
    </button>
  </div>

  <#-- 失败（默认展示） -->
  <div class="panel active" id="panel-fail" role="tabpanel">
    <#if failedCases?? && (failedCases?size > 0)>
      <table>
        <thead>
        <tr>
          <th style="width:70px">状态</th>
          <th>用例名</th>
          <th>类 / 方法</th>
          <th style="width:90px">耗时</th>
        </tr>
        </thead>
        <tbody>
        <#list failedCases as f>
          <tr class="case-row clickable" data-detail="fail-${f_index}" onclick="toggleDetail(this)">
            <td><span class="badge FAIL">FAIL</span></td>
            <td>${f.testName!?html}<span class="expand-hint"></span></td>
            <td class="small">${f.className!?html}<br/><span class="muted">${f.methodName!?html}</span></td>
            <td>${f.durationMs!} ms</td>
          </tr>
          <tr class="detail-row" id="fail-${f_index}">
            <td colspan="4">
              <div class="detail-panel">
                <div class="kv"><b>错误信息：</b>${f.errorMessage!?html}</div>
                <#if f.assertionMessage?? && f.assertionMessage != "">
                  <div class="kv"><b>断言：</b>${f.assertionMessage!?html}</div>
                  <div class="kv"><b>实际值：</b>${f.assertionActual!?html}</div>
                  <div class="kv"><b>期望值：</b>${f.assertionExpected!?html}</div>
                </#if>
                <div class="kv"><b>测试数据：</b></div>
                <pre>${(f.testData!'（无）')?html}</pre>
                <#if f.httpCalls?? && (f.httpCalls?size > 0)>
                  <div class="kv"><b>接口调用：</b></div>
                  <#list f.httpCalls as h>
                    <div class="http-item">
                      <div class="kv"><b>${h.method!?html}</b> ${h.url!?html} <span class="muted">(${h.costMs!} ms)</span></div>
                      <div class="muted small">Request</div>
                      <pre>${(h.requestBody!'')?html}</pre>
                      <div class="muted small">Response</div>
                      <pre>${(h.responseBody!'')?html}</pre>
                    </div>
                  </#list>
                <#else>
                  <div class="kv muted">无 HTTP 调用记录</div>
                </#if>
                <div class="kv"><b>失败堆栈：</b></div>
                <pre>${(f.stackTrace!'')?html}</pre>
              </div>
            </td>
          </tr>
        </#list>
        </tbody>
      </table>
    <#else>
      <div class="empty">本轮无失败用例</div>
    </#if>
  </div>

  <#-- 跳过 -->
  <div class="panel" id="panel-skip" role="tabpanel">
    <#if skippedCases?? && (skippedCases?size > 0)>
      <table>
        <thead>
        <tr>
          <th style="width:70px">状态</th>
          <th>用例名</th>
          <th>类 / 方法</th>
          <th style="width:90px">耗时</th>
        </tr>
        </thead>
        <tbody>
        <#list skippedCases as c>
          <tr>
            <td><span class="badge SKIP">SKIP</span></td>
            <td>${c.testName!?html}</td>
            <td class="small">${c.className!?html}<br/><span class="muted">${c.methodName!?html}</span></td>
            <td>${c.durationMs!} ms</td>
          </tr>
        </#list>
        </tbody>
      </table>
    <#else>
      <div class="empty">本轮无跳过用例</div>
    </#if>
  </div>

  <#-- 通过 -->
  <div class="panel" id="panel-pass" role="tabpanel">
    <#if passedCases?? && (passedCases?size > 0)>
      <table>
        <thead>
        <tr>
          <th style="width:70px">状态</th>
          <th>用例名</th>
          <th>类 / 方法</th>
          <th style="width:90px">耗时</th>
        </tr>
        </thead>
        <tbody>
        <#list passedCases as c>
          <tr>
            <td><span class="badge PASS">PASS</span></td>
            <td>${c.testName!?html}</td>
            <td class="small">${c.className!?html}<br/><span class="muted">${c.methodName!?html}</span></td>
            <td>${c.durationMs!} ms</td>
          </tr>
        </#list>
        </tbody>
      </table>
    <#else>
      <div class="empty">本轮无通过用例</div>
    </#if>
  </div>
</div>
<script>
  function switchTab(tab) {
    var buttons = document.querySelectorAll('.tab-btn');
    for (var i = 0; i < buttons.length; i++) {
      var active = buttons[i].getAttribute('data-tab') === tab;
      buttons[i].classList.toggle('active', active);
      buttons[i].setAttribute('aria-selected', active ? 'true' : 'false');
    }
    var panels = document.querySelectorAll('.panel');
    for (var j = 0; j < panels.length; j++) {
      panels[j].classList.toggle('active', panels[j].id === 'panel-' + tab);
    }
  }

  function toggleDetail(row) {
    var id = row.getAttribute('data-detail');
    var detail = document.getElementById(id);
    if (!detail) return;
    var open = detail.classList.toggle('open');
    row.classList.toggle('open', open);
  }
</script>
</body>
</html>
