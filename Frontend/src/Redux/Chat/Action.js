/* eslint-disable no-unused-vars */
import api from "@/Api/api";
import {
  CHAT_BOT_FAILURE,
  CHAT_BOT_REQUEST,
  CHAT_BOT_SUCCESS,
} from "./ActionTypes";

export const sendMessage = ({prompt,jwt}) => async (dispatch) => {
  dispatch({
    type: CHAT_BOT_REQUEST,
    payload:{prompt,role:"user"}
  });

  try {
    const { data } = await api.post("/chat/bot", { prompt }, {
      headers: {
        Authorization: `Bearer ${jwt}`,
      },
    });
  
    console.log("API Response Structure:", data);  // 👈 Important for inspection
  
    dispatch({
      type: CHAT_BOT_SUCCESS,
      payload: { ans: data, role: "model" }, // ✅ Modified to capture full data
    });
  } catch (error) {
    dispatch({ type: CHAT_BOT_FAILURE, payload: error });
    console.error("API Error:", error);
  }
  
};
