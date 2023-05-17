function loadShiftDetails() {
		$.ajax({
	        url: '/FactoryApplication/shift/getEmpShift.action',
	        type: 'GET',
	        headers: {
	        	Accept : "application/json;charset=utf-8","Content-type" : "application/json;charset=utf-8"
	        },
	        success: function(response) {
	        	console.log("response - ",response);
	            var tableData = document.getElementById("tableContent");
	            var table = "<center><table><th>Emp Id</th><th>Emp Name</th><th>Team Id</th><th>Team Name</th><th>ProductId</th><th>Product Name</th><th>Working Hrs(this month)</th><th>Extra Hrs</th>";
	            $.each(response,function(i,shift){
					console.log("shift : ",shift);
	            	table += "<tr>";
	                table += "<td>" + shift.empId + "</td>";
	                table += "<td>" + shift.empName + "</td>";
	                table += "<td>" + shift.teamId + "</td>";
	                table += "<td>" + shift.teamName + "</td>";
	                table += "<td>" + shift.productId + "</td>";
	                table += "<td>" + shift.productName + "</td>";
	                table += "<td>" + shift.workingHrs + "</td>";
	                table += "<td>" + shift.extraHrs + "</td></tr>";
	            })
	            table += "</table></center>";
	            tableData.innerHTML = table;
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	            console.log(jqXHR,"\n",textStatus,"\n", errorThrown);
	        }
	    });
	}
	
	function loadExtraShiftDetails() {
		$.ajax({
	        url: '/FactoryApplication/shift/getShiftSchedule.action',
	        type: 'GET',
	        headers: {
	        	Accept : "application/json;charset=utf-8","Content-type" : "application/json;charset=utf-8"
	        },
	        success: function(response) {
	        	console.log("response - ",response);
	            var tableData = document.getElementById("tableContent");
	            var table = "<center><table><th>Emp Id</th><th>Emp Name</th><th>ShiftDate</th><th>Number of Hours</th><th>StartTime</th><th>EndTime</th>";
	            $.each(response,function(i,shift){
					console.log("shift : ",shift);
	            	table += "<tr>";
	                table += "<td>" + shift.empId + "</td>";
	                table += "<td>"+  shift.empName + "</td>";
	                table += "<td>" + shift.shiftDate + "</td>";
	                table += "<td>" + shift.numOfHrs + "</td>";
	                table += "<td>" + shift.startTime + "</td>";
	                table += "<td>" + shift.endTime + "</td></tr>";
	            })
	            table += "</table></center>";
	            tableData.innerHTML = table;
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	            console.log(jqXHR,"\n",textStatus,"\n", errorThrown);
	        }
	    });
	    
	    $.ajax({
	        url: '/FactoryApplication/shift/getTeamShift.action',
	        type: 'GET',
	        headers: {
	        	Accept : "application/json;charset=utf-8","Content-type" : "application/json;charset=utf-8"
	        },
	        success: function(response) {
	        	console.log("response - ",response);
	            var tableData = document.getElementById("teamShiftContent");
	            var table = "<center><table><th>Team Id</th><th>Team Name</th><th>Team Size</th><th>Number of Eight Hr Shift</th><th>Number of Six Hr Shift</th><th>Extra Members Required Count</th><th>Extra Members Available Count</th>";
	            $.each(response,function(i,shift){
					console.log("shift : ",shift);
	            	table += "<tr>";
	                table += "<td>" + shift.teamId + "</td>";
	                table += "<td>"+  shift.teamName + "</td>";
	                table += "<td>" + shift.teamSize + "</td>";
	                table += "<td>" + shift.numOfEightHourShift + "</td>";
	                table += "<td>" + shift.numOfSixHourShift + "</td>";
	                table += "<td>" + shift.extraMembersRequiredCount + "</td>";
	                table += "<td>" + shift.extraMembersAvailableCount + "</td></tr>";
	            })
	            table += "</table></center>";
	            tableData.innerHTML = table;
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	            console.log(jqXHR,"\n",textStatus,"\n", errorThrown);
	        }
	    });
	}
	
	