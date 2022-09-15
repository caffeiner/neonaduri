import {getSat,getVisited,getSel} from '~/api/statistics'

export const state = () =>(
    {
        words:[],
        priceList:[],
        foodList:[],
        natureList:[],


    }
)


export const mutations = {
    SET_SAT(state, payload){
        payload.forEach(element => {
          if(element.satType===0){
            state.priceList.push(payload);
          }else if(element.satType===1){
            state.foodList.push(payload);
          }if(element.satType===0){
            state.natureList.push(payload);
          }
        });
    },
    SET_SEL(state, payload){
      payload.forEach(element=>{
        const arr=[];

        arr.push(element.selReason);
        arr.push(element.selPercent);

        state.words.push(arr);
      })
    }
}

export const getters ={

}


export const actions = {
    callSatList({commit}){
        getSat(
            ({data})=>{
                console.log(data)
                commit('SET_SAT',data)
            },
            (error)=>{
                console.log(error)
            }
        )
    },
    callSelList({commit}){
        getSel(
            ({data})=>{
                console.log(data)
                commit('SET_SEL',data)
            },
            (error)=>{
                console.log(error)
            }
        )
    },
    callVisitedList({commit}){
        getVisited(
            ({data})=>{
                commit('SET_VISITED',data)
            },
            (error)=>{
                console.log(error)
            }
        )
    }
}
