import tkinter as tk
from view.frm_login import FrmLogin

class ContactsSplashScreen(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Contacts Splash Screen")
        self.geometry("550x400")
        self.resizable(False, False)
        self._init_components()

    def _init_components(self):
        tk.Label(self, text="Demian's Contact Book", font=("Arial", 12)).pack(pady=(90, 25))
        tk.Label(self, text="Infinite Loops Developers for fun").pack(pady=(0, 35))

        info_frame = tk.Frame(self)
        info_frame.pack(pady=(0, 35))
        tk.Label(info_frame, text="(C)2026").pack(side=tk.LEFT, padx=50)
        tk.Label(info_frame, text="0.9.0").pack(side=tk.LEFT, padx=50)

        btn_continue = tk.Button(self, text="Continue", width=12, command=self._btn_continue_action)
        btn_continue.pack()

    def _btn_continue_action(self):
        self.destroy()
        frm_login = FrmLogin()
        frm_login.mainloop()