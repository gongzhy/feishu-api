pookui = function(me) {
	return me = {
		timer : null,
		fok : null,
		fyes : null,
		fno : null,
		body : function() {
			if (document.getElementsByTagName("body")) {
				return document.getElementsByTagName("body")[0]
			}
		},
		titleDiv : function() {
			if (document.getElementById("pook_ui_title")) {
				return document.getElementById("pook_ui_title")
			}
		},
		buttonDiv : function() {
			if (document.getElementById("pook_ui_button")) {
				return document.getElementById("pook_ui_button")
			}
		},
		remove : function() {
			clearInterval(pookui.timer);
			var alertDiv = document.getElementById("pook_ui_confirm");
			if (alertDiv) {
				pookui.body().removeChild(alertDiv)
			}
		},
		_show : function(conText) {
			pookui.remove();
			var div = document.createElement("div");
			div.setAttribute("id", "pook_ui_confirm");
			div.innerHTML = "<div style=\"position:fixed;_position:absolute;top:0;left:0;width:100%;height:100%;font-size:0;background:#000000;border:0 none;opacity:0.5;filter:alpha(opacity=50);z-index:99998;\"></div>" + "<div id=\"pook_ui_pup\" style=\"position:fixed;_position:absolute;left:0;top:20%;width:100%;z-index:99999;\">" + "<div id=\"pook_ui_content\" style=\"position:relative;width:85%;max-width:300px;_width:300px;margin:0 auto;font-family:Microsoft Yahei;background:#fff;border-radius:4px 4px 6px 6px; text-align:center;box-shadow:0 0 20px rgba(0, 0, 0, 0.2);overflow:hidden;\">" + "<h2 id=\"pook_ui_title\" style=\"line-height:2;font-weight:bold;font-size:16px;color:#85530A;text-align:left;text-indent:1em;border-bottom:1px solid #e8e8e8;\"></h2>" + "<div id=\"pook_ui_message\" style=\"padding:2em 1em;line-height:1.5;font-size:15px;color:#333;\">" + conText + "</div>" + "<div id=\"pook_ui_button\" style=\"position:relative;margin:0;padding:0;font-size:0;background:#f9f9f9;overflow:hidden;\"></div>" + "</div></div>";pookui.body().appendChild(div)
		},
		confirm : function(conText, fyes, fno) {
			pookui._show(conText);
			this.fyes = fyes;
			this.fno = fno;
			pookui.titleDiv().innerHTML = "确&nbsp;&nbsp;认";
			pookui.buttonDiv().innerHTML = "<a href=\"javascript:pookui.bno();\" style=\" display:inline-block;width:50%;line-height:3;;text-transform:uppercase;font-size:14px;color:#707070;background:#d9d9d9;text-decoration:none;\">取消</a>" + "<a href=\"javascript:pookui.byes();\" style=\"display:inline-block;width:50%;line-height:3;font-size:14px;color:white;background-color:#337ab7;text-decoration:none;\">确定</a>";pookui.setTop()
		},
		alert : function(conText, delayTime, fn) {
			pookui._show(conText);
			pookui.titleDiv().innerHTML = "提&nbsp;&nbsp;示";
			this.fok = fn;
			pookui.buttonDiv().innerHTML = "<a id=\"pook_ui_enter\" href=\"javascript:pookui.bok();\" style=\"display:block;position:relative;width:100%;line-height:3;font-size:14px;color:#666;text-decoration:none;z-index: 2;\">确定</a>";pookui.setTop();
			if (delayTime) {
				var div = document.createElement("div");
				div.style.fontSize = "0";
				div.innerHTML = "<p style=\"position: absolute;top:0;right:1em;font-size:14px;color:#999;text-align:center;line-height:3;z-index:1;\"><span id=\"pook_ui_delayTime\">" + delayTime + "</span>" + "秒后自动关闭" + "</p>";pookui.buttonDiv().appendChild(div);pookui.countdown(delayTime)
			}
		},
		error : function(conText, delayTime, fn) {
			pookui.alert(conText, delayTime, fn);pookui.state("error");
			pookui.titleDiv().innerHTML = "错&nbsp;&nbsp;误"
		},
		success : function(conText, delayTime, fn) {
			pookui.alert(conText, delayTime, fn);pookui.state("success");
			pookui.titleDiv().innerHTML = "成&nbsp;&nbsp;功"
		},
		bok : function() {
			pookui.call(this.fok)
		},
		byes : function() {
			pookui.call(this.fyes)
		},
		bno : function() {
			pookui.call(this.fno)
		},
		call : function(call) {
			call = call || pookui.voidFunction; call();pookui.remove();
		},
		state : function(type) {
			pookui.titleDiv().style.color = "#FFF";switch (type) {
			case "error":
				pookui.titleDiv().style.color = "#CF494A";
				break;case "success":
				pookui.titleDiv().style.color = "#7AD54F";
				break
			}
		},
		countdown : function(t) {
			clearInterval(pookui.timer);
			var delayTimeDiv = document.getElementById("pook_ui_delayTime");
			pookui.timer = setInterval(function() {
				if (t <= 1) {
					clearInterval(pookui.timer);pookui.bok()
				}
				t--;
				delayTimeDiv.innerHTML = t
			}, 1000)
		},
		setTop : function() {
			var pup = document.getElementById("pook_ui_pup");
			var pupHeight = pup.offsetHeight;
			var wHeight = document.documentElement.clientHeight;
			var pupTop = (wHeight - pupHeight) / 2 - pupHeight / 2;
			var pupBottom = wHeight - pupTop - pupHeight;
			if (pupTop > pupBottom || pupTop < pupHeight / 2) {
				pupTop = (wHeight - pupHeight) / 2
			} else if (pupTop <= 0) {
				pupTop = 0
			}
			pup.style.top = pupTop + "px"
		},
		keyEvent : function(e) {
			var e = e || window.event;
			switch (e.keyCode | e.which | e.charCode) {
			case 13:
				if (document.getElementById("pook_ui_enter") != null) {
					pookui.remove()
				}
				break;case 27:
			case 96:
				if (document.getElementById("pook_ui_confirm") != null) {
					pookui.remove()
				}
				break
			}
		},
		voidFunction : function() {}
	}
}();