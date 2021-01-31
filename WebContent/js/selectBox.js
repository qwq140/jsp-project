function changeDep(dep){
			var icn = ["김해", "대구"];
			var gmp = ["김해", "제주", "여수", "울산", "사천"];
			var pus =["인천","김포","제주"];
			var cju = ["김포", "김해", "광주", "군산", "대구", "무안", "여수", "울산", "원주", "사천", "청주", "포항"];
			var kwj = ["제주"];
			var kuv = ["제주"];
			var tae = ["인천", "제주"];
			var mwx = ["제주"];
			var rsu = ["김포","제주"];
			var usn = ["김포", "제주"];
			var wju = ["제주"];
			var hin = ["김포", "제주"];
			var cjj = ["제주"];
			var kpo = ["제주"];
			var target = document.getElementById("arrAirportNm");

			if(dep.value=="인천") var data = icn;
			else if(dep.value=="김포") var data = gmp;
			else if(dep.value=="김해") var data = pus;
			else if(dep.value=="제주") var data = cju;
			else if(dep.value=="광주") var data = kwj;
			else if(dep.value=="군산") var data = kuv;
			else if(dep.value=="대구") var data = tae;
			else if(dep.value=="무안") var data = mwx;
			else if(dep.value=="여수") var data = rsu;
			else if(dep.value=="울산") var data = usn;
			else if(dep.value=="원주") var data = wju;
			else if(dep.value=="사천") var data = hin;
			else if(dep.value=="청주") var data = cjj;
			else if(dep.value=="포항") var data = kpo;

			target.options.length=0;

			for(x in data){
				var opt = document.createElement("option");
				opt.value = data[x];
				opt.innerHTML = data[x];
				target.appendChild(opt);
			}
		}