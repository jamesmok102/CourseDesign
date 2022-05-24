import {request} from "./request";

export function adminLogin(data) {
    return request({
        url: '/adminLogin',
        method: 'post',
        data
    })
}
