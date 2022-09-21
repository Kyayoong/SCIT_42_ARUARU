/* globals Chart:false, feather:false */

(function () {
  'use strict'

  feather.replace()

  // Graphs
  let ctx1 = document.getElementById('myChart1')
  let ctx2 = document.getElementById('myChart2')
  let ctx3 = document.getElementById('myChart3')
  let ctx4 = document.getElementById('myChart4')
  // eslint-disable-next-line no-unused-vars
  
  //그래프1
  let myChart1 = new Chart(ctx1, {
    type: 'line',
    data: {
      labels: [
        '22/09/14',
        '22/09/15',
        '22/09/16',
        '22/09/17',
        '22/09/18',
      ],
      datasets: [{
        data: [
          108,
          203,
          198,
          221,
          241,
        ],
        lineTension: 0,
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
            beginAtZero: false
          }
        }]
      },
      legend: {
        display: false
      }
    }
  })
  
  //그래프2
  let myChart2 = new Chart(ctx2, {
    type: 'line',
    data: {
      labels: [
        '22/09/14',
        '22/09/15',
        '22/09/16',
        '22/09/17',
        '22/09/18',
      ],
      datasets: [{
        data: [
          108,
          203,
          198,
          221,
          241,
        ],
        lineTension: 0,
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
            beginAtZero: false
          }
        }]
      },
      legend: {
        display: false
      }
    }
  })
  
  //그래프3
  let myChart3 = new Chart(ctx3, {
    type: 'line',
    data: {
      labels: [
        '22/09/14',
        '22/09/15',
        '22/09/16',
        '22/09/17',
        '22/09/18',
      ],
      datasets: [{
        data: [
          108,
          203,
          198,
          221,
          241,
        ],
        lineTension: 0,
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
            beginAtZero: false
          }
        }]
      },
      legend: {
        display: false
      }
    }
  })
  //그래프4
  let myChart4 = new Chart(ctx4, {
    type: 'line',
    data: {
      labels: [
        '22/09/14',
        '22/09/15',
        '22/09/16',
        '22/09/17',
        '22/09/18',
      ],
      datasets: [{
        data: [
          108,
          203,
          198,
          221,
          241,
        ],
        lineTension: 0,
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
            beginAtZero: false
          }
        }]
      },
      legend: {
        display: false
      }
    }
  })
  
  
})()
