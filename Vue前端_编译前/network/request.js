import axios from 'axios';
import router from "@/router";

export function request(config) {
    const instance = axios.create({
        baseURL: 'http://1.12.253.71:8081',
        timeout: 5000
    })

    //请求拦截
    instance.interceptors.request.use(config=>{
        const token = window.localStorage.getItem('JWT_Token');
        const token2 = window.localStorage.getItem('JWT_Saler_Token')
        const token3 = window.localStorage.getItem('JWT_User_Token')
        if (token) {
            config.headers.Authorization = 'Bearer ' + token;
        }
        if (token2) {
            config.headers.Authorization2 = 'Bearer ' + token2;
        }
        if (token3) {
            config.headers.Authorization3 = 'Bearer ' + token3;
        }
        return config;
    })

    //响应拦截
    instance.interceptors.response.use(res=>{
        return res;
    },err=>{
        if(err.response.status == '401') {
            router.push({path:'/salerlogin'});
        }
    })

    return instance(config);
}