import {request} from "./request";

export function getOrderByUserId(id, page) {
    return request({
        url: `/saler/order_byuser/${id}/${page}`,
        method: 'get',
    })
}

export function getOrderByUserIdForUser(id, page) {
    return request({
        url: `/userislogin/order_byuser/${id}/${page}`,
        method: 'get',
    })
}

export function betchDeleteOrderById(data) {
    return request({
        url:'/saler/order/batchDelete',
        method:'delete',
        headers: {'content-type': 'application/json'},
        data: JSON.stringify(data)
    })
}

export function deleteOrderById(id) {
    return request({
        url:`/saler/order/${id}`,
        method:'delete',
    })
}

export function insertOrder(data) {
    return request({
        url:'/userislogin/insertOrder',
        method:'post',
        headers: {'content-type': 'application/json'},
        data: JSON.stringify(data)
    })
}