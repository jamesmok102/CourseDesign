import {request} from "./request";

export function insertUser(data) {
    return request({
        url:'/saler/user/insertUser',
        method:'post',
        headers: {'content-type': 'multipart/form-data'},
        data
    })
}

export function insertUserForUser(data) {
    return request({
        url:'/user/user/insertUser',
        method:'post',
        headers: {'content-type': 'multipart/form-data'},
        data
    })
}

export function getUser(page) {
    return request({
        url: `/saler/user/${page}`,
        method: 'get',
    })
}

export function deleteUserById(id) {
    return request({
        url:`/saler/user/${id}`,
        method:'delete',
    })
}

export function betchDeleteUserById(data) {
    return request({
        url:'/saler/user/batchDelete',
        method:'delete',
        headers: {'content-type': 'application/json'},
        data: JSON.stringify(data)
    })
}

export function getUserById(id) {
    return request({
        url:`/saler/user_id/${id}`,
        method:'get',
    })
}

export function getUserByIdForUser(id) {
    return request({
        url:`/userislogin/user_id/${id}`,
        method:'get',
    })
}

export function updateUser(data) {
    return request({
        url:'/saler/updateUser',
        method:'post',
        headers: {'content-type': 'application/json'},
        data: JSON.stringify(data)
    })
}

export function uploadUserImg(data) {
    return request({
        url:'/saler/updateUserImg',
        method:'post',
        headers: {'content-type': 'multipart/form-data'},
        data
    })
}

export function updateUserForUser(data) {
    return request({
        url:'/userislogin/updateUser',
        method:'post',
        headers: {'content-type': 'application/json'},
        data: JSON.stringify(data)
    })
}

export function uploadUserImgForUser(data) {
    return request({
        url:'/userislogin/updateUserImg',
        method:'post',
        headers: {'content-type': 'multipart/form-data'},
        data
    })
}