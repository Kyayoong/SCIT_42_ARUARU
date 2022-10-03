
(function () {

  feather.replace()
  let test= 1;
  // Graphs
  let ctx1 = document.getElementById('myChart1')
  let ctx2 = document.getElementById('myChart2')
  let ctx3 = document.getElementById('myChart3')
  let ctx4 = document.getElementById('myChart4')
  // eslint-disable-next-line no-unused-vars
  
  //일반 가입자
  let myChart1 = new Chart(ctx1, {
    type: 'line',
    data: {
      labels: [
        date5.dates,
        date4.dates,
        date3.dates,
        date2.dates,
        date1.dates
      ],
      datasets: [{
        data: [
          	date5.users_cnt,
        	date4.users_cnt,
        	date3.users_cnt,
        	date2.users_cnt,
        	date1.users_cnt
        ],
        backgroundColor: 'transparent',
        borderColor: '#ff4e0d',
        borderWidth: 2,
        pointBackgroundColor: '#ff4e0d'
      }]
    },
    options: {
      scales: {
        yAxes: [{
          ticks: {
            beginAtZero: true
          }
        }]
      },
      legend: {
        display: false
      }
    }
  })
  
  //식당 가입자
  let myChart2 = new Chart(ctx2, {
    type: 'line',
    data: {
      labels: [
        date5.dates,
        date4.dates,
        date3.dates,
        date2.dates,
        date1.dates
      ],
      datasets: [{
        data: [
          	date5.restaurant_cnt,
        	date4.restaurant_cnt,
        	date3.restaurant_cnt,
        	date2.restaurant_cnt,
        	date1.restaurant_cnt
        ],
        backgroundColor: 'transparent',
        borderColor: '#ff4e0d',
        borderWidth: 2,
        pointBackgroundColor: '#ff4e0d'
      }]
    },
    options: {
      scales: {
        yAxes: [{
          ticks: {
            beginAtZero: true
          }
        }]
      },
      legend: {
        display: false
      }
    }
  })
  
  //리뷰수
  let myChart3 = new Chart(ctx3, {
    type: 'line',
    data: {
      labels: [
        date5.dates,
        date4.dates,
        date3.dates,
        date2.dates,
        date1.dates
      ],
      datasets: [{
        data: [
	        date5.allreview_cnt,
	        date4.allreview_cnt,
	        date3.allreview_cnt,
	        date2.allreview_cnt,
	        date1.allreview_cnt
        ],
        backgroundColor: 'transparent',
        borderColor: '#ff4e0d',
        borderWidth: 2,
        pointBackgroundColor: '#ff4e0d'
      }]
    },
    options: {
      scales: {
        yAxes: [{
          ticks: {
            beginAtZero: true
          }
        }]
      },
      legend: {
        display: false
      }
    }
  })
  //일일방문자
  let myChart4 = new Chart(ctx4, {
    type: 'line',
    data: {
      labels: [
        date5.dates,
        date4.dates,
        date3.dates,
        date2.dates,
        date1.dates
      ],
      datasets: [{
        data: [
	        date5.visit_cnt,
	        date4.visit_cnt,
	        date3.visit_cnt,
	        date2.visit_cnt,
	        date1.visit_cnt
        ],
        backgroundColor: 'transparent',
        borderColor: '#ff4e0d',
        borderWidth: 2,
        pointBackgroundColor: '#ff4e0d'
      }]
    },
    options: {
      scales: {
        yAxes: [{
          ticks: {
            beginAtZero: true
          }
        }]
      },
      legend: {
        display: false
      }
    }
  })
  
  
})()
