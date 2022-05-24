import {request} from "./request";

export function insertGood(data) {
    return request({
        url:'/saler/insertGood',
        method:'post',
        headers: {'content-type': 'multipart/form-data'},
        data
    })
}

export function getGoodAll(page) {
    return request({
        url: `/saler/good/${page}`,
        method: 'get',
    })
}

export function getGoodSell(page) {
    return request({
        url: `admin/goodsell/${page}`,
        method: 'get',
    })
}

export function getGoodAllCount0(page) {
    return request({
        url: `/admin/good0/${page}`,
        method: 'get',
    })
}

export function getGoodAllForAdmin(page) {
    return request({
        url: `/admin/good/${page}`,
        method: 'get',
    })
}

export function getGoodAllForUser(page) {
    return request({
        url: `/user/good/${page}`,
        method: 'get',
    })
}

export function getGoodBySaler(id, page) {
    return request({
        url: `/saler/good_bysaler/${id}/${page}`,
        method: 'get',
    })
}

export function deleteGoodById(id) {
    return request({
        url:`/saler/good/${id}`,
        method:'delete',
    })
}

export function betchDeleteGoodById(data) {
    return request({
        url:'/saler/good/batchDelete',
        method:'delete',
        headers: {'content-type': 'application/json'},
        data: JSON.stringify(data)
    })
}

export function getGoodById(id) {
    return request({
        url:`/saler/good_id/${id}`,
        method:'get',
    })
}

export function updateGood(data) {
    return request({
        url:'/saler/updateGood',
        method:'post',
        headers: {'content-type': 'application/json'},
        data: JSON.stringify(data)
    })
}

export function uploadGoodImg(data) {
    return request({
        url:'/saler/updateGoodImg',
        method:'post',
        headers: {'content-type': 'multipart/form-data'},
        data
    })
}

export function getRandomGood(count) {
    return request({
        url:`/user/randomgood/${count}`,
        method:'get',
    })
}

export function getGoodByIdFroUser(id) {
    return request({
        url:`/user/good_id/${id}`,
        method:'get',
    })
}

export function updateCountOfBroweringGood(id) {
    return request({
        url:`/user/updateBrowse/${id}`,
        method:'post',
    })
}
