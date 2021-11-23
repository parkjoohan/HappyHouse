import { apiInstance } from "./index.js";

const api = apiInstance();

function cctvList(dongCode, success, fail) {
  api.get(`cctv/cctvLocation/${dongCode}`).then(success).catch(fail);
}

export { cctvList };
