import {request} from "./request";

export function getBrowseRecordByUserId(id, page) {
    return request({
        url: `/saler/browse/${id}/${page}`,
        method: 'get',
    })
}