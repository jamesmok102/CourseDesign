import {request} from "./request";

export function getRecordAll(page) {
    return request({
        url:`/admin/record/${page}`,
        method:'get',
    })
}