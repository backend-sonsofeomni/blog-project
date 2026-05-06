const apiClient = axios.create({
    baseURL: "http://localhost:8080",  // 기본 URL
    timeout: 5000,                     // 요청 제한 시간 (5초)
    headers: {                         // 기본 Header 설정
        "Content-Type": "application/json"
    }
});
export const commentPost = (path,createRequest)=>apiClient.post(path,createRequest);

export const commentDelete = (path) => apiClient.delete(path);