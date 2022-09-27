<template>
  <div class="statistics">
    <div class="white-back">
      <div class="map">
        <div id="myMap" style="width: 600px; height: 400px">
        </div>
      </div>
      <div class="under">
        <div id="wordCloud">
          <vue-word-cloud
            style="height: 50vh; width: 40vw"
            :words="wordList"
            :color="
              ([, weight]) =>
                weight > 10 ? 'black' : weight > 5 ? 'RoyalBlue' : 'Indigo'
            "
            font-family="Roboto"
          />
        </div>
        <div id="main" style="width: 100%; height: 100%"></div>
      </div>
    </div>
  </div>
</template>

<script>
import * as echarts from 'echarts' // echart를 전역으로 불러옴
import VueWordCloud from 'vuewordcloud'
import axios from 'axios';
import { mapActions, mapState } from 'vuex'


export default {
  // head:{
  //   script:[
  //     {src:'~static/map/koreaMap.json'}
  //   ]
  // },
  name: '',
  components: {
    [VueWordCloud.name]: VueWordCloud,
  },
  data() {
    return {
      wordList: [
        ['romance', 10.4],
        ['horror', 3],
        ['fantasy', 7],
        ['adventure', 3],
        ['하이요', 4],
        ['히히히히히힣', 11],
      ],
      fontSizeMapper: (word) => Math.log2(word.value) * 5,
    }
  },
  computed: {
    ...mapState('statistics', [
      'words',
      'priceList',
      'foodList',
      'natureList',
      'regionList',
    ]),
  },
  created() {
    // this.callSatList()
    // this.callSelList()
    // this.callVisitedList()
    
  },
  mounted() {
    this.mapOpen();
    // this.cowTest();
    this.graphOpen();
  },
  methods: {
    ...mapActions('statistics', [
      'callSatList',
      'callSelList',
      'callVisitedList',
    ]),
    mapOpen(){

      const chartDom = document.getElementById('myMap');
      const myChart = echarts.init(chartDom);
      let option;
      myChart.showLoading();
      
      axios.get('data/asset/geo/USA.json')
      .then((usaJson)=> {
        console.log(JSON.stringify(usaJson,null,2))
        myChart.hideLoading();
        echarts.registerMap('usa', usaJson, {
          Alaska: {
            left: -131,
            top: 25,
            width: 15
          },
          Hawaii: {
            left: -110,
            top: 28,
            width: 5
          },
          'Puerto Rico': {
            left: -76,
            top: 26,
            width: 2
          }
        });
        option = {
          title: {
            text: '잘들어왔으',
            left: 'right'
          },
          tooltip: {
            trigger: 'item',
            showDelay: 0,
            transitionDuration: 0.2
          },
          visualMap: {
            left: 'right',
            min: 500000,
            max: 38000000,
            inRange: {
              color: [
                '#313695',
                '#4575b4',
                '#74add1',
                '#abd9e9',
                '#e0f3f8',
                '#ffffbf',
                '#fee090',
                '#fdae61',
                '#f46d43',
                '#d73027',
                '#a50026'
              ]
            },
            text: ['최대', '최소'],
            calculable: true
          },
          // toolbox: {
          //   show: true,
          //   // orient: 'vertical',
          //   left: 'left',
          //   top: 'top',
          //   feature: {
          //     dataView: { readOnly: false },
          //     restore: {},
          //     saveAsImage: {}
          //   }
          // },
          series: [
            {
              name: '방문횟수',
              type: 'map',
              roam: true,
              map: 'usa',
              emphasis: {
                label: {
                  show: true
                }
              },
              data: [
                { name: 'Alabama', value: 4822023 },
                { name: 'Alaska', value: 731449 },
                { name: 'Arizona', value: 6553255 },
                { name: 'Arkansas', value: 2949131 },
                { name: 'California', value: 38041430 },
                { name: 'Colorado', value: 5187582 },
                { name: 'Connecticut', value: 3590347 },
                { name: 'Delaware', value: 917092 },
                { name: 'District of Columbia', value: 632323 },
                { name: 'Florida', value: 19317568 },
                { name: 'Georgia', value: 9919945 },
                { name: 'Hawaii', value: 1392313 },
                { name: 'Idaho', value: 1595728 },
                { name: 'Illinois', value: 12875255 },
                { name: 'Indiana', value: 6537334 },
                { name: 'Iowa', value: 3074186 },
                { name: 'Kansas', value: 2885905 },
                { name: 'Kentucky', value: 4380415 },
                { name: 'Louisiana', value: 4601893 },
                { name: 'Maine', value: 1329192 },
                { name: 'Maryland', value: 5884563 },
                { name: 'Massachusetts', value: 6646144 },
                { name: 'Michigan', value: 9883360 },
                { name: 'Minnesota', value: 5379139 },
                { name: 'Mississippi', value: 2984926 },
                { name: 'Missouri', value: 6021988 },
                { name: 'Montana', value: 1005141 },
                { name: 'Nebraska', value: 1855525 },
                { name: 'Nevada', value: 2758931 },
                { name: 'New Hampshire', value: 1320718 },
                { name: 'New Jersey', value: 8864590 },
                { name: 'New Mexico', value: 2085538 },
                { name: 'New York', value: 19570261 },
                { name: 'North Carolina', value: 9752073 },
                { name: 'North Dakota', value: 699628 },
                { name: 'Ohio', value: 11544225 },
                { name: 'Oklahoma', value: 3814820 },
                { name: 'Oregon', value: 3899353 },
                { name: 'Pennsylvania', value: 12763536 },
                { name: 'Rhode Island', value: 1050292 },
                { name: 'South Carolina', value: 4723723 },
                { name: 'South Dakota', value: 833354 },
                { name: 'Tennessee', value: 6456243 },
                { name: 'Texas', value: 26059203 },
                { name: 'Utah', value: 2855287 },
                { name: 'Vermont', value: 626011 },
                { name: 'Virginia', value: 8185867 },
                { name: 'Washington', value: 6897012 },
                { name: 'West Virginia', value: 1855413 },
                { name: 'Wisconsin', value: 5726398 },
                { name: 'Wyoming', value: 576412 },
                { name: 'Puerto Rico', value: 3667084 }
              ]
            }
          ]
        };
        myChart.setOption(option);
      });

      option && myChart.setOption(option);

    },
    graphOpen(){
        // Initialize the echarts instance based on the prepared dom
      const myChart = echarts.init(document.getElementById('main'))
        // Specify the configuration items and data for the chart
      const option = {
        tooltip: {},
        legend: {
          data: ['물가', '식당 및 음식', '자연경관'],
        },
        xAxis: {
          data: [
            '서울',
            '부산',
            '대구',
            '인천',
            '광주',
            '대전',
            '울산',
            '세종',
            '경기',
            '강원',
            '충북',
            '충남',
            '전북',
            '전남',
            '경북',
            '경남',
            '제주',
          ],
          name: '도시',
        },
        yAxis: [
          {
            type: 'value',
            name: 'percent',
            min: 0,
            max: 100,
            position: 'left',
            axisLabel: {
              formatter: '{value} %',
            },
          },
        ],
        series: [
          {
            name: '물가',
            type: 'line',
            // data: [15, 70, 64, 57, 87, 100]
            data: this.priceList,
          },
          {
            name: '식당 및 음식',
            type: 'line',
            // data: [10, 23, 5, 17, 3, 34]
            data: this.foodList,
          },
          {
            name: '자연경관',
            type: 'line',
            // data: [43, 33,75, 67, 93, 64]
            data: this.natureList,
          },
        ],
      }

      // Display the chart using the configuration items and data just specified.
      myChart.setOption(option)
    },
    
  },
}
</script>

<style scoped>
.statistics {
  width: 100%;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

.white-back {
  background-image: url('/banner/statistics-background.png');
  background-position-y: 100%;
  background-position-x: 20px;
  background-repeat: no-repeat;
  background-size: 100% 130%;
  width: 100%;
  height: 100vh;
}

.map {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 50%;
}

.under {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 50%;
}

#main {
  width: 60%;
}

#wordCloud {
  width: 50%;
}
</style>
