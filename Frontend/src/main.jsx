import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import Login from './pages/Login.jsx'
import { BrowserRouter,createBrowserRouter,RouterProvider } from 'react-router-dom'
import Dashboard from './pages/Dashboard.jsx'
import Homework from './pages/Homework.jsx'
import AIFeature from './pages/AiFeature.jsx'
import Resources from './pages/Resources.jsx'
import Revision from './pages/Revision.jsx'
import Settings from './pages/Settings.jsx'
import Timetable from './pages/Timetable.jsx'

const router=createBrowserRouter([
  {path:"/", element:<Login/>},
  {path:"/dashboard", element: <Dashboard/>},
  {path:"/homework",element: <Homework/>},
  {path:"/aifeature",element: <AIFeature/>},
  {path:"/resources",element: <Resources/>},
  {path:"/revision",element: <Revision/>},
  {path:"/settings",element: <Settings/>},
  {path:"/timetable",element: <Timetable/>}
])
createRoot(document.getElementById('root')).render(
  <RouterProvider router={router} />
)