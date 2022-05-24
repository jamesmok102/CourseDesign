import {request} from "./request";

export function insertCategory(data) {
    return request({
        url: '/saler/insertCategory',
        method: 'post',
        headers: {'content-type': 'application/json'},
        data: JSON.stringify(data)
    })
}

export function getCategory(page) {
    return request({
        url: `/saler/category/${page}`,
        method: 'get',
    })
}

export function getCategoryOrder(page) {
    return request({
        url: `/admin/categoryOrder/${page}`,
        method: 'get',
    })
}

export function getCategoryNoPage() {
    return request({
        url: "/saler/category_nopage",
        method: 'get',
    })
}

export function deleteCategoryById(id) {
    return request({
        url: `/saler/category/${id}`,
        method:'delete',
    })
}

export function betchDeleteCategoryById(data) {
    return request({
        url:'/saler/category/batchDelete',
        method:'delete',
        headers: {'content-type': 'application/json'},
        data: JSON.stringify(data)
    })
}