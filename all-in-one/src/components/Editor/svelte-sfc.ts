// generated by GPT-4

class SvelteSFCState {
  constructor(public state: string) {}

  clone(): SvelteSFCState {
    return new SvelteSFCState(this.state);
  }

  equals(other: SvelteSFCState): boolean {
    return this.state === other.state;
  }
}
const SvelteSFCStateHtml = new SvelteSFCState("html");
const SvelteSFCStateJavascript = new SvelteSFCState("javascript");
const SvelteSFCStateCss = new SvelteSFCState("css");

export const svelteSfc: any = {
  getInitialState: function () {
    return SvelteSFCStateHtml.clone();
  },
  tokenize: function (line: any, state: SvelteSFCState) {
    let tokens: any = [];
    let offset = 0;

    function addToken(type: any, length: any) {
      tokens.push({
        startIndex: offset,
        scopes: type,
      });
      offset += length;
    }

    if (state.equals(SvelteSFCStateHtml)) {
      if (line.trim().startsWith("<script")) {
        state = SvelteSFCStateJavascript.clone();
      } else if (line.trim().startsWith("<style")) {
        state = SvelteSFCStateCss.clone();
      } else {
        addToken("html", line.length);
      }
    } else if (state.equals(SvelteSFCStateJavascript)) {
      if (line.trim().startsWith("</script")) {
        state = SvelteSFCStateHtml.clone();
      } else {
        addToken("javascript", line.length);
      }
    } else if (state.equals(SvelteSFCStateCss)) {
      if (line.trim().startsWith("</style")) {
        state = SvelteSFCStateHtml.clone();
      } else {
        addToken("css", line.length);
      }
    } else {
      state = SvelteSFCStateHtml.clone();
    }

    return {
      tokens: tokens,
      endState: state,
    };
  },
};
