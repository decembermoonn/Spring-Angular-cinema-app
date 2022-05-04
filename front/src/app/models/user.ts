interface User {
  username: string;
  authData?: string;
}

export type OptionalUser = User | undefined;
