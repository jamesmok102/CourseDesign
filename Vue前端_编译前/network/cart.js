import {request} from "./request";

export function getCart(page) {
    return request({
        url: `/saler/cart/${page}`,
        method: 'get',
    })
}

export function deleteCartById(id) {
    return request({
        url:`/saler/cart/${id}`,
        method:'delete',
    })
}

export function betchDeleteCartById(data) {
    return request({
        url:'/saler/cart/batchDelete',
        method:'delete',
        headers: {'content-type': 'application/json'},
        data: JSON.stringify(data)
    })
}

export function getCartByUserId(id, page) {
    return request({
        url: `/saler/cart_byuser/${id}/${page}`,
        method: 'get',
    })
}

export function userDealwithCart(data) {
    return request({
        url:'/userislogin/dealwithcart',
        method:'post',
        headers: {'content-type': 'application/json'},
        data: JSON.stringify(data)
    })
}

export function getCartByUserIdAllForUser(id, page) {
    return request({
        url: `/userislogin/cart_byuser/${id}/${page}`,
        method: 'get',
    })
}

export function deleteCartByIdForUser(id) {
    return request({
        url:`/userislogin/cart/${id}`,
        method:'delete',
    })
}

export function betchDeleteCartByIdForUser(data) {
    return request({
        url:'/userislogin/cart/batchDelete',
        method:'delete',
        headers: {'content-type': 'application/json'},
        data: JSON.stringify(data)
    })
}

export function changeSelected(data, id) {
    return request({
        url:`/userislogin/cart/selected/${id}`,
        method:'post',
        headers: {'content-type': 'application/json'},
        data: JSON.stringify(data)
    })
}