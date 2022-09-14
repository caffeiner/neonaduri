import {getSat,getVisited,getSel} from '~/api/statistics'

export const state = () =>(
    {
        words:[],
        priceList:[],
        foodList:[],
        natureList:[]

    }
)


export const mutations = {
    SET_SAT(state, payload){
        payload.array.forEach(element => {
            if(element.satType==0){

            }else if(element.satType==1){

            }else{

            }
        });
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
                console.log(data)
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