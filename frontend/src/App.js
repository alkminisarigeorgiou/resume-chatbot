import { useState, useRef, useEffect } from "react";
import Linkify from 'linkify-react';
import { Send } from "lucide-react";

function App() {
  const [messages, setMessages] = useState([
    { text: "Hi! Ask me anything about my resume.", isBot: true }
  ]);
  const [prompt, setPrompt] = useState("");
  const [loading, setLoading] = useState(false);
  const messagesEndRef = useRef(null);

  const scrollToBottom = () => {
    messagesEndRef.current?.scrollIntoView({ behavior: "smooth" });
  };

  const linkifyOptions = {
    target: '_blank',
    rel: 'noopener noreferrer',
    className: "underline text-blue-600 hover:text-blue-800",
  };

useEffect(() => {
  document.querySelector("html").setAttribute("data-theme", "dark");
}, []);
  useEffect(() => {
    scrollToBottom();
  }, [messages]);

  const handleSend = async (text = prompt) => {
    if (!text.trim()) return;

    setMessages((prev) => [...prev, { text, isBot: false }]);
    setPrompt("");
    setLoading(true);

    try {
      const res = await fetch("http://localhost:8080/chat", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ message: text })
      });

      const botReply = await res.text();
      setMessages((prev) => [...prev, { text: botReply, isBot: true }]);
    } catch (err) {
      setMessages((prev) => [
        ...prev,
        { text: "Error: Could not reach the server.", isBot: true }
      ]);
    }

    setLoading(false);
  };

  const quickReplies = ["Tell me about yourself", "Skills", "Education", "Projects", "Languages"];

  return (
    <div className="min-h-screen bg-base-300 flex items-center justify-center p-4">
      <div className="w-full max-w-xl bg-base-100 shadow-lg rounded-xl p-6 flex flex-col space-y-4">
        <h1 className="text-2xl font-bold">Get to Know me: Using this chatbot :)</h1>

        {/* Chat bubble area */}
        <div className="h-[400px] overflow-y-auto space-y-4">
          {messages.map((msg, idx) => (
            <div
              key={idx}
              className={`chat ${msg.isBot ? "chat-start" : "chat-end"}`}
            >
              {msg.isBot && (
                <div className="chat-image avatar">
                  <div className="w-8 rounded-full">
                    <img src="/bot.jpg" alt="bot" />
                  </div>
                </div>
              )}

            <div className={`chat-bubble whitespace-pre-wrap ${msg.isBot ? "bg-base-200" : "bg-primary text-white"}`}>
              <Linkify options={linkifyOptions}>{msg.text}</Linkify>
            </div>

            </div>
          ))}
          <div ref={messagesEndRef} />
        </div>

        {/* Quick replies */}
        <div className="flex gap-2 flex-wrap">
          {quickReplies.map((text, i) => (
            <button
              key={i}
              onClick={() => handleSend(text)}
              className="btn btn-sm rounded-full bg-base-200 hover:bg-base-300 text-base-content border border-base-300"
            >
              {text}
            </button>
          ))}
        </div>

        {/* Input */}
        <div className="flex items-center gap-2 bg-base-200 rounded-full px-4 py-2">
          <input
            type="text"
            className="flex-1 bg-base-200 outline-none text-sm"
            placeholder="Type your message..."
            value={prompt}
            onChange={(e) => setPrompt(e.target.value)}
            onKeyDown={(e) => e.key === "Enter" && handleSend()}
          />
          <button
            onClick={() => handleSend()}
            className="bg-gradient-to-tr from-purple-500 to-indigo-500 hover:from-purple-600 hover:to-indigo-600 text-white p-2 rounded-full shadow-md transition-all duration-200"
            disabled={loading}
          >
            <Send size={16} className="stroke-white" />
          </button>
        </div>
      </div>
    </div>
  );
}

export default App;
