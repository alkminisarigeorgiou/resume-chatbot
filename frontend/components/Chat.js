import React, { useState, useRef, useEffect } from 'react';
import ChatBubble from './ChatBubble';

export default function Chat() {
  const [messages, setMessages] = useState([
    { text: "Hi! Ask me anything about my resume.", isBot: true }
  ]);
  const [input, setInput] = useState('');
  const messagesEndRef = useRef(null);

  const handleSend = (text) => {
    if (!text.trim()) return;

    const newMessages = [...messages, { text, isBot: false }];
    setMessages(newMessages);

    setTimeout(() => {
      const botReply = getBotReply(text);
      setMessages((prev) => [...prev, { text: botReply, isBot: true }]);
    }, 600);
    setInput('');
  };

  const handleQuickReply = (text) => {
    setInput(text);
    handleSend(text);
  };

  useEffect(() => {
    messagesEndRef.current?.scrollIntoView({ behavior: "smooth" });
  }, [messages]);

  return (
    <div className="max-w-xl mx-auto mt-10 p-4 border rounded-lg bg-white shadow">
      <h2 className="text-xl font-bold mb-4">Resume Chatbot</h2>

      <div className="h-[400px] overflow-y-auto bg-gray-50 p-4 rounded-md">
        {messages.map((msg, index) => (
          <ChatBubble key={index} message={msg.text} isBot={msg.isBot} />
        ))}
        <div ref={messagesEndRef} />
      </div>

      <div className="flex gap-2 mt-4 flex-wrap">
        {["Who are you", "Your skills", "Show education"].map((text) => (
          <button
            key={text}
            className="px-3 py-1 bg-gray-100 hover:bg-gray-200 rounded-full text-sm border"
            onClick={() => handleQuickReply(text)}
          >
            {text}
          </button>
        ))}
      </div>

      <div className="flex mt-4">
        <textarea
          value={input}
          onChange={(e) => setInput(e.target.value)}
          className="flex-1 border rounded-l-md p-2"
          placeholder="Type your message..."
        />
        <button
          onClick={() => handleSend(input)}
          className="bg-blue-500 text-white px-4 rounded-r-md"
        >
          Send
        </button>
      </div>
    </div>
  );
}

const getBotReply = (text) => {
  if (text.toLowerCase().includes("who")) {
    return "I am a student in Management Science and Technology...";
  } else if (text.toLowerCase().includes("skills")) {
    return "I specialize in Software and Data Analysis Technologies...";
  } else if (text.toLowerCase().includes("education")) {
    return "I studied at the Athens University and Technical University of Munich.";
  }
  return "I'm not sure. Can you rephrase?";
};
