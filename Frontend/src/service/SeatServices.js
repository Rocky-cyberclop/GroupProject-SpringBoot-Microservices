import API from "./API";

export const getListSeatBySchedule = async (id) => {
  try {
    const response = await API.get("/seat/list", { params: { id: id } });
    return response.data;
  } catch (error) {
    console.error("Error fetching data:", error);
  }
};

export const bookingSeat = async (id, idSchedule) => {
  try {
    const response = await API.post("/seat/booking", {
      id: id,
      idSchedule: idSchedule,
    });
    return response.data;
  } catch (error) {
    console.error("Error fetching data:", error);
  }
};
