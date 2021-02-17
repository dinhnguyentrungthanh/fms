function overviewFeedback(){
  document.querySelector(".chart").style.display="block"; 
}
function detailFeedBack(){
  document.querySelector(".detail").style.display="inline-flex"; 
}
function drawChart() {
  // Tạo data table

    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Users');
    data.addColumn('number', 'Total');
    data.addRows([
      ['Strongly Disagree', 43],
      ['Disagree', 41],
      ['Neutral', 41],
      ['Agree', 40],
      ['Strongly Agree', 42]
    ]);
    
    // Set option của biểu đồ
    var options = {
      'title': '',
      'awidth': 800,
      'height': 400
    };

    // Vẽ biểu đồ từ data và option đã khai báo
    var chart = new google.visualization.PieChart(document.getElementById('chart'));
    chart.draw(data, options);
}

function drawChartTopic() {
  // Tạo data table

    var topic1 = new google.visualization.DataTable();
    topic1.addColumn('string', 'Users');
    topic1.addColumn('number', 'Total');
    topic1.addRows([
      ['10A1', 43],
      ['10A2', 41],
      ['10A3', 41],
      ['10A4', 40],
      ['10A5', 42]
    ]);
    var topic2 = new google.visualization.DataTable();
    topic2.addColumn('string', 'Users');
    topic2.addColumn('number', 'Total');
    topic2.addRows([
      ['Strongly Disagree', 43],
      ['Disagree', 41],
      ['Neutral', 41],
      ['Agree', 40],
      ['Strongly Agree', 42]
    ]);
    var topic3 = new google.visualization.DataTable();
    topic3.addColumn('string', 'Users');
    topic3.addColumn('number', 'Total');
    topic3.addRows([
      ['10A1', 43],
      ['10A2', 41],
      ['10A3', 41],
      ['10A4', 40],
      ['10A5', 42]
    ]);        

    // Set option của biểu đồ
    var optionTopic1 = {
      'title': 'Training Program & content',
      'awidth': 1000,
      'height': 200
    };

    var optionTopic2 = {
      'title': 'Trainer Course',
      'awidth': 1000,
      'height': 200
    };

    var optionTopic3 = {
      'title': 'Course organiztion',
      'awidth': 1000,
      'height': 200
    };        

    // Vẽ biểu đồ từ data và option đã khai báo
    var chartTopic1 = new google.visualization.PieChart(document.getElementById('topic1'));
    chartTopic1.draw(topic1, optionTopic1);
    var chartTopic2 = new google.visualization.PieChart(document.getElementById('topic2'));
    chartTopic2.draw(topic2, optionTopic2);
    var chartTopic3 = new google.visualization.PieChart(document.getElementById('topic3'));
    chartTopic3.draw(topic3, optionTopic3);        
  }

function success(){
	document.querySelector("#success").style.display="block";
}
function removeElement(){
            // confirm
            Swal.fire({
            title: 'Chắc chắn xóa?',
            text: "Sau khi xóa sẽ không lấy lại dữ liệu được!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Đồng ý!',
            cancelButtonText: 'Không đồng ý!'
            }).then((result) => {
                if (result.value) {
                    // gửi request lên server
                        Swal.fire({
                            position: 'bottom-end',
                            icon: 'success',
                            title: 'Đã xóa',
                            showConfirmButton: false,
                            timer: 1500
                        });
                }
            })
}