import React from 'react';

export default function ChatBubble({ message, isBot }) {
  return (
    <div className={`flex ${isBot ? 'justify-start' : 'justify-end'} mb-2`}>
      <div className={`rounded-2xl px-4 py-2 max-w-xs text-sm ${
        isBot ? 'bg-gray-200 text-black' : 'bg-blue-500 text-white'
      }`}>
        {message}
      </div>
    </div>
  );
}