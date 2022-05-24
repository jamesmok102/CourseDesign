import {request} from "./request";

export function getOrderDetailByOrderId(id, page) {
    return request({
        url: `/saler/orderdetail_byorder/${id}/${page}`,
        method: 'get',
    })
}

export function getOrderDetailByOrderIdForUser(id, page) {
    return request({
        url: `/userislogin/orderdetail_byorder/${id}/${page}`,
        method: 'get',
    })
}

export function betchDeleteOrderDetailById(data) {
    return request({
        url:'/saler/orderdetail/batchDelete',
        method:'delete',
        headers: {'content-type': 'application/json'},
        data: JSON.stringify(data)
    })
}

export function deleteOrderDetailById(id) {
    return request({
        url:`/saler/orderdetail/${id}`,
        method:'delete',
    })
}

export function getOrderDetailBySalerId(id, page) {
    return request({
        url: `/admin/orderdetail_bysaler/${id}/${page}`,
        method: 'get',
    })
}

export function getOrderDetailByGoodIdForAdmin(id, page) {
    return request({
        url: `/admin/orderdetail_bygood/${id}/${page}`,
        method: 'get',
    })
}

