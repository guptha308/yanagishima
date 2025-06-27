import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { create } from 'zustand';
import Header from './components/Header';
import HomePage from './components/HomePage';

interface AppState {
  unload: boolean;
  setUnload: (val: boolean) => void;
}

export const useAppStore = create<AppState>((set) => ({
  unload: false,
  setUnload: (val) => set({ unload: val }),
}));

export default function App() {
  return (
    <Router>
      <div className="min-h-screen" data-testid="app">
        <Header />
        <Routes>
          <Route path="/" element={<HomePage />} />
        </Routes>
      </div>
    </Router>
  );
}
