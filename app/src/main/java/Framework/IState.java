package Framework;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface IState {
	public void Init();
		// 이상태로 바뀌었을때 실행될것들
	public void Destroy();
		// 다른상태로 바뀔때 실행될것들
	public void Update();
		// 지속적으로 수행할것들
	public void Render(Canvas canvas);
		// 그려줘야할것들

	public boolean onTouchEvent(MotionEvent event);
		// 터치입력처리
	
}
