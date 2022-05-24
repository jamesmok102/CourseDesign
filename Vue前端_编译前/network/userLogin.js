import {request} from "./request";

export function userLogin(data) {
    return request({
        url: '/userlogin',
        method: 'post',
        data
    })
}