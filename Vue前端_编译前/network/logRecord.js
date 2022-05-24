import {request} from "./request";

export function insertLogRecord(data) {
    return request({
        url:'/logrecord',
        method:'post',
        headers: {'content-type': 'application/json'},
        data: JSON.stringify(data)
    })
}

export function getLogRecordAll(page) {
    return request({
        url:`/admin/logrecord/${page}`,
        method:'get',
    })
}