function loadEmployeeDetails() {
		$.ajax({
	        url: '/FactoryApplication/payroll/getEmployeeDetails.action',
	        type: 'GET',
	        headers: {
	        	Accept : "application/json;charset=utf-8","Content-type" : "application/json;charset=utf-8"
	        },
	        success: function(response) {
	        	console.log("response - ",response);
	            var tableData = document.getElementById("tableContent");
	            var table = "<center><table><th>Emp Id</th><th>Emp Name</th><th>DOB</th><th>PhoneNo</th><th>Email</th><th>team</th><th>Role</th><th>Reporting to</th><th>Joining Date</th><th>PayGrade</th>";
	            $.each(response,function(i,employee){
					console.log("employee : ",employee);
	            	table += "<tr>";
	                table += "<td>" + employee.empId + "</td>";
	                table += "<td>" + employee.empName + "</td>";
	                table += "<td>" + employee.dob + "</td>";
	                table += "<td>" + employee.phoneNo + "</td>";
	                table += "<td>" + employee.email + "</td>";
	                table += "<td>" + employee.team + "</td>";
	                table += "<td>" + employee.role + "</td>";
	                table += "<td>" + employee.mentor + "</td>";
	                table += "<td>" + employee.joiningDate + "</td>";
	                table += "<td>" + employee.payGrade + "</td></tr>";
	            })
	            table += "</table></center>";
	            tableData.innerHTML = table;
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	            console.log(jqXHR,"\n",textStatus,"\n", errorThrown);
	        }
	    });
	}
function loadAttendance() {
		$.ajax({
	        url: '/FactoryApplication/payroll/getEmployeeAttendance.action',
            type: 'GET',
            headers: {
                Accept : "application/json;charset=utf-8",
                "Content-type" : "application/json;charset=utf-8"
            },
            success: function(response) {
                console.log("response - ",response);
                var tableData = document.getElementById("tableContent");
                var table = "<center><table><th>Emp Id</th><th>Emp Name</th><th>Team</th><th>Date</th><th>Status</th>";
                $.each(response,function(i,employee){
                    console.log("employee : ",employee);
                    table += "<tr>";
                    table += "<td>" + employee.empId + "</td>";
                    table += "<td>" + employee.empName + "</td>";
                    table += "<td>" + employee.team + "</td>";
                    table += "<td>" + employee.date + "</td>";
                    table += "<td>" + employee.status + "</td></tr>";
                })
                table += "</table></center>";
                tableData.innerHTML = table;
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log(jqXHR,"\n",textStatus,"\n", errorThrown);
                var tableData = document.getElementById("tableContent");
                tableData.innerHTML="<center><h3>No Records Found</h3></center>"
            }
        });
	}
