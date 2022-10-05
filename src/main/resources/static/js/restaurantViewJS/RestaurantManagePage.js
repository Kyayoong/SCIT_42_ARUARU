
(function () {

  feather.replace()
  let test= 1;
  // Graphs
  let ctx1 = document.getElementById('myChart1')
  let ctx2 = document.getElementById('myChart2')
  let ctx3 = document.getElementById('myChart3')
  //let ctx4 = document.getElementById('myChart4')
  // eslint-disable-next-line no-unused-vars
  
  //예약
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
          	date5.reservation_cnt,
        	date4.reservation_cnt,
        	date3.reservation_cnt,
        	date2.reservation_cnt,
        	date1.reservation_cnt
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
  
  //리뷰
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
          	date5.review_cnt,
        	date4.review_cnt,
        	date3.review_cnt,
        	date2.review_cnt,
        	date1.review_cnt
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
  
  //찜
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
	        date5.zzim_cnt,
	        date4.zzim_cnt,
	        date3.zzim_cnt,
	        date2.zzim_cnt,
	        date1.zzim_cnt
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
  /*let myChart4 = new Chart(ctx4, {
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
  })*/
  
  
})()
