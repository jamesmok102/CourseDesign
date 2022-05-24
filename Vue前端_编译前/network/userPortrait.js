import {request} from "./request";

export function check(page) {
    return request({
        url: `/admin/userportrait/${page}`,
        method: 'get',
    })
}