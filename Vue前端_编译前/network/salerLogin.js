import {request} from "./request";

export function salerLogin(data) {
    return request({
        url: '/salerLogin',
        method: 'post',
        data
    })
}
